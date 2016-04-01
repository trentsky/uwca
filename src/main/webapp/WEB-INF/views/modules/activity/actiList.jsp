<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>运营管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
	    	return false;
	    }
		
		function display(){
			new PopupLayer({trigger:"#ele1",popupBlk:"#blk1",closeBtn:"#close1"});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/event/activity/">运营推广列表</a></li>
		<shiro:hasPermission name="event:acti:edit"><li><a href="${ctx}/event/activity/form?sort=10">运营信息添加</a></li></shiro:hasPermission>
	</ul>
<%-- 	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="breadcrumb form-search"> --%>
<%-- 		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/> --%>
<%-- 		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/> --%>
<%-- 		<label>类型：</label><form:select id="type" path="type" class="input-medium"><form:option value="" label=""/><form:options items="${typeList}" htmlEscape="false"/></form:select> --%>
<%-- 		&nbsp;&nbsp;<label>描述 ：</label><form:input path="description" htmlEscape="false" maxlength="50" class="input-medium"/> --%>
<!-- 		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/> -->
<%-- 	</form:form> --%>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>ID</th>
				<th>平台与版本</th>
				<th>投放位置</th>
				<th>投放城市</th>
				<th>投放次数</th>
				<th>标题</th>
				<th>图片</th>
				<th>URL</th>
				<th>投放日期</th>
				<th>添加时间</th>
				<th>申请人</th>
				<th>跟新日期</th>
				<shiro:hasPermission name="event:acti:edit">
					<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="acti">
			<tr>
				<td>${acti.id}</td>
				<td>${acti.pversion}</td>
				<td>${acti.location}</td>
				<td>${acti.areas}</td>
				<td>${acti.showtype}</td>
				<td>${acti.title}</td>
				<td><a href="${acti.picurl}">查看</a></td>
				<td>${acti.picurl}</td>
				<td>${acti.pushtime}</td>
				<td>${acti.createtime}</td>
				<td>${acti.username}</td>
				<td>${acti.edittime}</td>
				
				<shiro:hasPermission name="sys:dict:edit">
					<td>
	    				<a href="${ctx}/sys/dict/form?id=${acti.id}">修改</a>
						<a href="${ctx}/sys/dict/delete?id=${acti.id}&type=${dict.acti}" onclick="return confirmx('确认要删除该运营信息吗？', this.href)">删除</a>
	    				<a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.acti}&sort=${acti.sort+10}'><c:param name='description' value='${acti.title}'/></c:url>">添加键值</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>