package ru.iskhakov.walletsapi.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.iskhakov.walletsapi.request.SrvByIdRq;
import ru.iskhakov.walletsapi.response.SrvByIdRs;

@Component
@AllArgsConstructor
public class WalletSerializer {

    private Gson gson;

    @PostConstruct
    public void init() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
    }

    public <T> T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public <T> String serialize(T response) {
        return gson.toJson(response);
    }

}
