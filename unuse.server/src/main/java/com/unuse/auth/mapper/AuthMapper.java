package com.unuse.auth.mapper;


import com.unuse.auth.api.MenuItem;
import com.unuse.common.SessionData;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Unuse on 2016/10/29.
 */

@Repository
public interface AuthMapper {


    /** MenuItem **/

    List<MenuItem> getMenuItemList();

    void addMenuItem(MenuItem menuItem);

    void deleteMenuItemById(@Param("id") Integer id);

    void updateMenuItemById(MenuItem menuItem);


    /** Session Data **/

    void addSessionData(SessionData sessionData);

    SessionData getSessionDataByToken(String token);
}