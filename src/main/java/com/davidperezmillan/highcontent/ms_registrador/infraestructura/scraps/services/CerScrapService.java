package com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.services;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.ScrapPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Scene;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.mappers.CerScrapMapper;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.models.CerScrapResponse;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CerScrapService implements ScrapPort {

    private String URL = "https://cerdas.com";

    @Override
    public Scene[] allScrap() {
        return CerScrapMapper.map(scrapePage(URL));
    }


    private CerScrapResponse[] scrapePage(String url) {
        List<CerScrapResponse> result = new ArrayList<>();
        try{
            // Conectar y obtener el documento HTML de la página
            Document document = Jsoup.connect(url).get();

            // obtener todos los elementos con class video_box
            Elements elements = document.select(".video_box");
            // get title
            for (Element element : elements) {
                CerScrapResponse cerScrapResponse = new CerScrapResponse();

                Element link = element.selectFirst("a");
                String hrefAttrib = link.attr("href");
                String urlElement = url + hrefAttrib;
                cerScrapResponse.setDescription(getDescripcion(urlElement));

                Element imagen = element.selectFirst("img");
                cerScrapResponse.setTitle(imagen.attr("title"));

                cerScrapResponse.setImage(imagen.attr("src"));
                log.info("cerScrapResponse: {}", cerScrapResponse);

                result.add(cerScrapResponse);
            }

            return result.toArray(new CerScrapResponse[0]);
        }catch (Exception e){
            return null;

        }
    }

    private String getDescripcion(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Element videoInfo = document.selectFirst(".video_info");
            return videoInfo.text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
