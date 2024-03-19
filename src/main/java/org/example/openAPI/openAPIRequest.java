package org.example.openAPI;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.component.OpenAPIComponent;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Component
public class openAPIRequest {
    private final OpenAPIComponent openAPIComponent;
    private final ScheduledAnnouncement scheduledAnnouncement= new ScheduledAnnouncement();
    private String nowDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    private String nowTime = scheduledAnnouncement.getAnnouncementTime();


    public APIResult openAPIRequestHttp(int nx,int ny) {
        String urlLink = openAPIComponent.getLink()+"?serviceKey="+openAPIComponent.getServiceKey()
                +"&numOfRows="+openAPIComponent.getNumOfRows()
                +"&dataType="+openAPIComponent.getDataType()
                +"&base_date="+nowDate+"&base_time="+nowTime;

        BufferedReader reader = null;
        APIResult apiResult =null;
        try {
            URL url = new URL(urlLink + "&nx=" + nx + "&ny=" + ny);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            //int responsenCode= connection.getResponseCode();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            apiResult = new APIResult(new JSONObject(response.toString()));
            reader.close();

            // 응답 출력
            //System.out.println("Response: " + response.toString());
        } catch (MalformedURLException e) {
            log.error("telegram Error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            log.error("telegram Error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("telegram Error: " + e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (reader != null) try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return apiResult;
    }
}
