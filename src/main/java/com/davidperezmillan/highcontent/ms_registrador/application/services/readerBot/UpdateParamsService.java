package com.davidperezmillan.highcontent.ms_registrador.application.services.readerBot;

import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ChatsPort;
import com.davidperezmillan.highcontent.ms_registrador.application.ports.readerBot.ParamsPort;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Chat;
import com.davidperezmillan.highcontent.ms_registrador.domain.model.Param;
import com.davidperezmillan.highcontent.ms_registrador.domain.usecases.readerBot.UpdateParamsUseCase;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class UpdateParamsService implements UpdateParamsUseCase {

    private final ParamsPort paramsPort;
    private final ChatsPort chatsPort;

    public UpdateParamsService(ParamsPort paramsPort, ChatsPort chatsPort) {
        this.paramsPort = paramsPort;
        this.chatsPort = chatsPort;
    }

    @Override
    public Param updateParam(Param param) {
        var parambbdd = paramsPort.getParam(param);
        param.setType(parambbdd.getType());// recover the type of the bbdd
        param.setId(parambbdd.getId());// recover the id of the bbdd

        Param paramProccess = deleteDuplicateValues(param);
        if (paramProccess.getType().equals("chats")) {
            paramProccess = checkChatsExist(paramProccess);
        }
        return paramsPort.updateParam(paramProccess);
    }




    private Param checkChatsExist(Param paramProccess) {
        String[] values = paramProccess.getValue().split(",");
        Set<String> uniqueStrings = new HashSet<>(Arrays.asList(values));
        Iterator<String> iterator = uniqueStrings.iterator();
        while (iterator.hasNext()) {
            try {
                String chatId = iterator.next();
                Chat chat = new Chat();
                chat.setId(Long.parseLong(chatId.trim()));
                Chat chatrecover = chatsPort.getChat(chat);
                if (chatrecover == null) {
                    // Remove the chat from the list
                    iterator.remove();
                }
            } catch (Exception e) {
                // Remove the chat from the list
                iterator.remove();
            }
        }
        paramProccess.setValue(convertArrayToString(uniqueStrings.toArray(new String[0])));
        return paramProccess;
    }


    private Param deleteDuplicateValues(Param param) {
        String[] values = param.getValue().split(",");
        String[] uniqueValues = skipDuplicateString(values);
        param.setValue(convertArrayToString(uniqueValues));
        return param;
    }

    private String convertArrayToString(String[] array) {
        return String.join(",", array);
    }

    private String[] skipDuplicateString(String[] array) {
        Set<String> uniqueStrings = new HashSet<>(Arrays.asList(array));
        return uniqueStrings.toArray(new String[0]);
    }
}
