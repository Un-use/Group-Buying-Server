package com.unuse.user.api;

import com.unuse.common.ResponseResult;

import java.util.List;

/**
 * Created by unuse on 17/2/24.
 */

public class UserAddressListResult extends ResponseResult {

	private List<UserAddress> addressList;

	public List<UserAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<UserAddress> addressList) {
		this.addressList = addressList;
	}
}
