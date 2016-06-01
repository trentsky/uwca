<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>公司管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/company/list">公司信息</a></li>
	</ul><br/>
	<form:form id="searchForm" modelAttribute="cpy" action="${ctx}/company/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户名称：</label><form:input path="username"  htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>公司名称：</label><form:input path="companyname" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="clearfix"></li>
			<li><label>注册日期起：</label><input path="starttime" id="starttime" type="text" readonly="readonly" maxlength="20" class="input-medium"
				value="<fmt:formatDate value="${cpy.starttime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li><label>注册日期止：</label><input path="endtime" id="endtime" type="text" readonly="readonly" maxlength="20" class="input-medium"
				value="<fmt:formatDate value="${cpy.endtime}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>用户名</th><th>手机号</th><th>公司名称</th><th>注册时间</th><th>操作</th></thead>
		<c:forEach items="${page.list}" var="cpy">
			<tr>
				<td>${cpy.username}</td>
				<td>${cpy.mobile}</td>
				<td>${cpy.companyname}</td>
				<td>${cpy.createtime}</td>
				<shiro:hasPermission name="sys:user:edit"><td>
					<a href="${ctx}/company/getCompanyByid?id=${cpy.id}">审核</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>