package com.hp.service;

import com.hp.dao.BaseDao;
import com.hp.model.Url;

public interface UrlService extends BaseDao<Url> {
	public Url getRoleByUrl(String url);
}
