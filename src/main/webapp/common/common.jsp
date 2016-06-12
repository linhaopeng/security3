<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String cxt = request.getContextPath();
	request.setAttribute("cxt", cxt);
%>
<script type="text/javascript" src="${cxt}/jslib/jquery-easyui-1.3.5/jquery.min.js"></script>
<script type="text/javascript" src="${cxt}/jslib/changeTheme/jquery.cookie.js"></script>
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${cxt}/jslib/jquery-easyui-1.3.5/themes/gray/easyui.css">
<script type="text/javascript" src="${cxt}/jslib/changeTheme/changeEasyuiTheme.js"></script>
<link rel="stylesheet" type="text/css" href="${cxt}/jslib/jquery-easyui-1.3.5/themes/icon.css">
<script type="text/javascript" src="${cxt}/jslib/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${cxt}/jslib/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<!-- upload -->
<script type="text/javascript" src="${cxt}/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${cxt}/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>

<script type="text/javascript" src="${cxt}/jslib/extendEasyUI.js"></script>
<script type="text/javascript" src="${cxt}/jslib/extendJquery.js"></script>
