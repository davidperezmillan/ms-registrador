package com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.series.services;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.scraps.series.models.SerieResponse;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class SerieService {

    private String URL = "https://wolfmax4k.com/";
    private String URL_SERIE = "series/720p/";

    public List<SerieResponse> allSeries() {
        List<SerieResponse> result = new ArrayList<>();
        try {
            // Configurar Jsoup para aceptar todos los certificados SSL
            // useCustomCertificate("wolfmax4k.crt");
            disableSSLValidation();

            // Conectar y obtener el documento HTML de la página
            Document document = Jsoup.connect(URL + URL_SERIE).get();

            // obtener todos los elementos con class video_box
            Elements elements = document.select(".col-lg-2");
            // get title
            for (Element element : elements) {
                SerieResponse SerieResponse = new SerieResponse();

                Element link = element.selectFirst("a");
                String hrefAttrib = link.attr("href");
                String urlElement = "http:" + hrefAttrib;

                Element imagen = link.selectFirst("img");
                SerieResponse.setImage(imagen.attr("src"));

                Element title = link.selectFirst("h3");
                SerieResponse.setTitle(title.ownText());
                result.add(SerieResponse);
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void useCustomCertificate(String certPath) {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream fis = cargarCertificado(certPath);
            X509Certificate caCert = (X509Certificate) cf.generateCertificate(fis);

            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            ks.load(null, null);
            ks.setCertificateEntry("caCert", caCert);

            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, tmf.getTrustManagers(), new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private InputStream cargarCertificado(String certPath) throws Exception {
        ClassPathResource resource = new ClassPathResource(certPath);
        return resource.getInputStream();
    }

    public static void disableSSLValidation() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/**
 * <div class="col-lg-2">
 * 				<a href="//wolfmax4k.com/series/720p/doctor-odyssey" class="card card-movie">
 * 					<div class="card-overlay">
 * 						<img class="img-fluid rounded-1" src="//wolfmax4k.com/assets/u/p/c/13227_1732440302-Doctor-Odyssey.jpg" alt="Doctor Odyssey" data-pic="c/13227_1732440302-Doctor-Odyssey.jpg" width="250" height="375" loading="lazy">
 * 						<div class="card-playsh"><i class="fa fa-play"></i></div>
 * 						<div class="quality-ms"><div class="quality">720p</div></div>
 * 							<div class="typeCont">
 * 								<span class="cont">720p</span>
 * 							</div>
 * 						</div>
 * 						<div class="card-body">
 * 						<h3 class="title">Doctor Odyssey</h3>
 * 						<div class="list-inline list-separator fs-xs text-muted mb-1">
 * 							<span class="fdi-item">720p</span>
 * 							<span class="dot"></span>
 * 							<span class="float-right fdi-type">2024</span>
 * 						</div>
 * 					</div>
 * 				</a>
 * 			</div>
 */