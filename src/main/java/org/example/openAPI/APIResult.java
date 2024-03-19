package org.example.openAPI;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class APIResult {
    private JSONObject json;


    public APIResult(JSONObject json){
        this.json=json;
    }

    //openAPI 응답값 + 체감온도 형태의 JSON
    public JSONObject plusJson(JSONObject json){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("OpenAPI_httpResponse",this.json);
        jsonObject.put("Wind_Chill",json);

        return jsonObject;
    }

    //체감온도 구하는 메소드
    public JSONObject calculateWindChill(){
        final int T1H_start_index = 24;     //온도 부분 시작 index
        final int REH_start_index = 30;     //습도 부분 시작 index
        final int WSD_start_index = 54;     //풍속 부분 시작 index
        final int Forecast_Count = 6;       //예보 개수 -> 초단기예보는 6시간 이내의 예보를 한다.

        JSONArray itemArray = json.getJSONObject("response")
                .getJSONObject("body")
                .getJSONObject("items")
                .getJSONArray("item");

        int nowMonth= Integer.valueOf(String.valueOf(LocalDate.now().getMonthValue()));

        JSONArray windChillList = new JSONArray();
        JSONObject windChill = new JSONObject();

        /**
         *
         * 5~9월: 여름철 체감온도
         * 10~익년4월: 겨울철 체감온도(단, 기온 10°C 이하, 풍속 1.3 m/s 이상일때만)
         *
         * */
        for(int i=0;i<Forecast_Count;i++){
            if(nowMonth > 4 && nowMonth < 10 || (itemArray.getJSONObject(T1H_start_index + i).getDouble("fcstValue") > 10.0 &&
                    itemArray.getJSONObject(WSD_start_index + i).getDouble("fcstValue") < 1.3)){
                windChill.put("fcstTime",itemArray.getJSONObject(T1H_start_index+i).getString("fcstTime"));
                windChill.put("fcstValue", getInSummer(itemArray.getJSONObject(T1H_start_index+i).getDouble("fcstValue")
                        ,itemArray.getJSONObject(REH_start_index+i).getDouble("fcstValue")));
            }else{
                windChill.put("fcstTime",itemArray.getJSONObject(T1H_start_index+i).getString("fcstTime"));
                windChill.put("fcstValue", getInWinter(itemArray.getJSONObject(T1H_start_index+i).getDouble("fcstValue")
                        ,itemArray.getJSONObject(WSD_start_index+i).getDouble("fcstValue")));
            }
            windChillList.put(windChill);
        }

        JSONObject resultWindChill = new JSONObject();
        resultWindChill.put("windChill", windChillList);

        return resultWindChill;
    }

    // 출처: https://tekken5953.tistory.com/42 [개발새발 - IT 기술블로그:티스토리]
    private double getTw(double ta, double rh) {
        return ta * Math.atan(0.151977 * Math.pow(rh + 8.313659, 0.5)) +
                Math.atan(ta + rh) - Math.atan(rh - 1.67633) +
                (0.00391838 * Math.pow(rh, 1.5) * Math.atan(0.023101 * rh)) - 4.686035;
    }
    private double getInSummer(double ta, double rh){
        double tw =getTw(ta, rh);

        return -0.2442 + (0.55399 * tw) + (0.45535 * ta) - (0.0022 * Math.pow(tw, 2.0)) + (0.00278 * tw * ta) + 3.0;
    }
    private double getInWinter(double ta, double v) {
        v*=3.6; //수정 부분 -> m/s-> km/h로 단위변환
        return 13.12 + (0.6215 * ta) - (11.37 * Math.pow(v, 0.16)) + (0.3965 * Math.pow(v, 0.16) * ta);
    }
}
