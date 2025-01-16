package com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.converters;

import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.FichaResponse;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.EpisodeGroup;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.Info;
import com.davidperezmillan.highcontent.ms_registrador.infraestructura.info.models.commons.ResultFicha;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@Log4j2
public class CustomEpisodeDeserializer extends JsonDeserializer<FichaResponse> {



    @Override
    public FichaResponse deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        log.info("CustomEpisodeDeserializer");
        JsonNode rootNode = parser.getCodec().readTree(parser);
        FichaResponse response = new FichaResponse();

        // Navigate to the nested "episodes" field
        if (rootNode.has("result")) {
            JsonNode resultNode = rootNode.get("result");
            ResultFicha resultFicha = new ResultFicha();
            if (resultNode.has("info")) {
                JsonNode infoNode = resultNode.get("info");
                Info info = new Info();
                if (infoNode.has("episodes")) {
                    JsonNode episodesNode = infoNode.get("episodes");
                    log.info("episodesNode: " + episodesNode);
                    if (episodesNode.isArray()) {
                        for (JsonNode node : episodesNode) {
                            List<EpisodeGroup> episodeGroups = parser.getCodec().treeToValue(node, List.class);
                            info.setListEpisodes(episodeGroups);
                        }
                        info.setEpisodes(episodesNode.size());
                    } else if (episodesNode.isTextual()) {
                        info.setEpisodes(episodesNode.asInt());
                    }
                }
                // Deserialize other fields in Info if needed
                // info.setOtherField(infoNode.get("otherField").asText());
                resultFicha.setInfo(info);
            }
            // Deserialize other fields in ResultFicha if needed
            // resultFicha.setOtherField(resultNode.get("otherField").asText());
            response.setResult(resultFicha);
        }
        // Deserialize other fields in FichaResponse if needed
        // response.setOtherField(rootNode.get("otherField").asText());

        return response;
    }
}
