package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.DAO.LocationDao;
import org.example.domain.Location;

import java.util.List;

@Mapper
public interface LocationMapper {
    @Select("select location.nx,location.ny from location.location")
    List<LocationDao> tester();
    @Select("select nx,ny from location.location where city=#{city} and district=#{district} and neighborhood=#{neighborhood}")
    LocationDao find(@Param("city") String city,@Param("district") String district,@Param("neighborhood") String neighborhood );

    @Select("select district from location.location where city=#{city} order by district ASC;")
    List<String> findToDistrict(@Param("city") String city);

    @Select("select neighborhood from location.location where district=#{district} order by neighborhood ASC;")
    List<String> findToNeighborhood(@Param("district") String district);

}
