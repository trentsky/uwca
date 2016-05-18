package com.uwca.operation.modules.api.sys.entity.vo;

import com.uwca.operation.common.utils.BaseEntity;

public class VerifyCodeVo extends BaseEntity {

	private static final long serialVersionUID = 1L;
	private Result result;

	public class Result {

		private String code;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
