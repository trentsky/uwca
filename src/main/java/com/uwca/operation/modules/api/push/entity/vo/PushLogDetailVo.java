package com.uwca.operation.modules.api.push.entity.vo;

import com.uwca.operation.common.utils.BaseEntity;

public class PushLogDetailVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		private PushLogDetailResult pushLogDetailResult;

		public PushLogDetailResult getPushLogDetailResult() {
			return pushLogDetailResult;
		}

		public void setPushLogDetailResult(PushLogDetailResult pushLogDetailResult) {
			this.pushLogDetailResult = pushLogDetailResult;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
}
