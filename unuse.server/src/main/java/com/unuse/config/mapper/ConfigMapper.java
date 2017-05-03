package com.unuse.config.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by CNQ on 2016/11/10.
 */

@Repository
public interface ConfigMapper {

    String getConfigValueByName(@Param("name") String name);

}
