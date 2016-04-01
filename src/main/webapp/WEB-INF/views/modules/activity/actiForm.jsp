<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var selectItem = new Array();
			$(".cityMain").live("click",function(){
				var pn = $(this).siblings("label").text();
				if($(this).attr("checked")){
					var pi = $(this).val();
					if($.inArray(pn, selectItem) == -1){
						selectItem.push(pn);
					}
					$.ajax({
						url : '${ctx}/event/activity/getCities?pi=' + pi,
						dataType : "json",
						success : function(msg){
							if(typeof(msg) != "undefined" && msg.length > 0){
								var finalHtml = [];
								finalHtml.push('<table id="cityBox_')
								finalHtml.push(pi);
								finalHtml.push('" style="background-color: #fff;" class="table table-bordered responsive-utilities"><tbody><tr><td>')
								for(var i=0;i<msg.length;i++){
									finalHtml.push('<span><input name="cities" class="cityMain" checked="checked" type="checkbox" value="');
									finalHtml.push(msg[i].areaid);
									finalHtml.push('"><label>');
									finalHtml.push(msg[i].name);
									finalHtml.push('</label></span>');
									if($.inArray(msg[i].name, selectItem) == -1){
										selectItem.push(msg[i].name);
									}
								}
								finalHtml.push('</td></tr></tbody></table>');
								$("#showCitiesBox").find("table").hide().end().append(finalHtml.join(""));
								$("#selectBox").empty().append(selectItem.join(","));
							}
						}
					});
				}else{
					selectItem.splice($.inArray(pn, selectItem), 1);
				}
				$("#selectBox").empty().append(selectItem.join(","));
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/event/acti/">活动详情</a></li>
		<li class="active"><a href="${ctx}/event/acti/save?id=${acti.id}">活动<shiro:hasPermission name="event/acti/edit">${not empty acti.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="event:acti:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="acti" action="${ctx}/event/activity/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<c:if test="${!empty acti.id}">
			<div class="control-group">
			<label class="control-label">ID:</label>
			<div class="controls">
				<label class="control-label">${zcti.id}</label>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">平台与版本:</label>
			<div class="controls">
				iPhone:<form:checkboxes path="pmlist" items="${iphone}" itemLabel="version" itemValue="id" htmlEscape="false" class="required"/>
				<br/>
				Android:<form:checkboxes path="pmlist" items="${android}" itemLabel="version" itemValue="id" htmlEscape="false" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投放位置:</label>
			<div class="controls">
				<form:radiobutton path="location" value="0"/>推荐
				<form:radiobutton path="location" value="1"/>论坛
				<form:radiobutton path="location" value="2"/>找车
				<form:radiobutton path="location" value="3"/>发现
				<form:radiobutton path="location" value="4"/>我
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投放城市:</label>
			<div class="controls" id="selectBox">
				
			</div>
			<div class="controls" id="showCitiesBox">
				<form:checkboxes path="provinces" items="${cities}" itemLabel="name" itemValue="areaid" htmlEscape="false" cssClass="cityMain" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投放次数:</label>
			<div class="controls">
				<form:radiobutton path="showtype" value="1"/>1次
				<form:radiobutton path="showtype" value="2"/>每天一次
				<form:radiobutton path="showtype" value="3"/>每次启动app
				<form:radiobutton path="showtype" value="4"/>打开链接后不再提示
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题:</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">活动图片:</label>
			<div class="controls">
				<form:hidden id="picurl" path="picurl" htmlEscape="false" maxlength="255" class="input-xlarge"/>
				<sys:ckfinder input="picurl" type="images" uploadPath="/picurl" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">后续动作:</label>
			<div class="controls">
				打开URL <form:input path="picurl" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">投放日期:</label>
			<div class="controls">
				<label>日期范围：&nbsp;</label><input id="startpushtime" name="startpushtime" class="required" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endpushtime" name="endpushtime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="event:acti:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>