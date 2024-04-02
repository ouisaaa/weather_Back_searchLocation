package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.DAO.LocationDao;
import org.example.mapper.LocationMapper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

@RequiredArgsConstructor
@Service
public class WeatherService {
    private final LocationMapper locationMapper;

    public TreeSet<String> searchDistrict(String city){
        TreeSet<String> districtList = new TreeSet<>(locationMapper.findToDistrict(city)); //중복제거와 정렬을 위해 TreeSet 사용

        return districtList;
    }
    public List<String> searchNeighborhood(String district){
        return locationMapper.findToNeighborhood(district);
    }

}
