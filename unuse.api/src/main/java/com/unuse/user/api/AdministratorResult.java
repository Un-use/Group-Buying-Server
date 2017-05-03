package com.unuse.user.api;

import com.unuse.common.ResponseResult;

/**
 * Created by Unuse on 2016/10/29.
 */

public class AdministratorResult extends ResponseResult {

    private Administrator administrator;

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }
}
