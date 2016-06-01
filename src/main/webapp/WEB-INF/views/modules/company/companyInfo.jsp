<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司详细信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<style> 
		.divcss5 img{max-width:300px;_width:expression(this.width > 300 ? "300px" : this.width);} 
	</style> 
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/company/list">公司信息</a></li>
		<li class="active"><a href="${ctx}/company/getCompanyByid?id=${cpy.id}">公司审核</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="cpy" action="${ctx}/company/updateCompany" method="post" class="form-horizontal">
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">用户名:</label>
			<div class="controls">
				<form:input path="username" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司名称:</label>
			<div class="controls">
				<form:input path="companyname" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">传真:</label>
			<div class="controls">
				<form:input path="fax" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="mail" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">网址:</label>
			<div class="controls">
				<form:input path="website" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">公司地址:</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="80" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">法人:</label>
			<div class="controls">
				<form:input path="legalperson" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机构代码:</label>
			<div class="controls">
				<form:input path="organizationcode" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册时间:</label>
			<div class="controls">
				<form:input path="createtime" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="divcss5">
			<label class="control-label">营业执照:</label>
			<div class="controls">
				<c:choose>
					<c:when test="${cpy.businesslicense != null}">
						<img src="${ctx}/company/getBusinesslicense?businesslicense=${cpy.businesslicense}" >
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="control-group">
		</div>
		<div class="control-group">
			<label class="control-label">审核意见:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
</body>
</html>