package com.uwca.operation.modules.api.assure.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;
import com.uwca.operation.modules.api.assure.entity.po.Assure;

public class AssureVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		List<Assure> assures = new ArrayList<Assure>();

		public List<Assure> getAssures() {
			return assures;
		}

		public void setAssures(List<Assure> assures) {
			this.assures = assures;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
