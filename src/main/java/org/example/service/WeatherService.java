package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.DAO.LocationDao;
import org.example.domain.Location;
import org.example.mapper.LocationMapper;
import org.example.openAPI.APIResult;
import org.example.openAPI.openAPIRequest;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final LocationMapper locationMapper;
    private final openAPIRequest openAPIRequest;
    public List<LocationDao> test(){
        return locationMapper.tester();
    }

    public LocationDao searchLocation(String city, String district, String neighborhood){
       return locationMapper.find(city, district, neighborhood);
//        openAPIRequest.openAPIRequestHttp(findLocation.getNx(), findLocation.getNy());
    }
    public JSONObject httpResultJSON(LocationDao location){
        APIResult apiResult =openAPIRequest.SrtNcst(location.getNx(), location.getNy());
        return apiResult.plusJson(apiResult.calculateWindChill());
    }
    public TreeSet<String> searchDistrict(String city){
        TreeSet<String> districtList = new TreeSet<>(locationMapper.findToDistrict(city)); //중복제거와 정렬을 위해 TreeSet 사용

        return districtList;
    }
    public List<String> searchNeighborhood(String district){
        return locationMapper.findToNeighborhood(district);
    }

}
