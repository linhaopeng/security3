<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加角色信息',
			url :  '${cxt}/role/editUI.action',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看用户信息',
			url : '${cxt}/role/editUI.action?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : '${cxt}/role/editUI.action?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post('${cxt}/role/delete.action', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var grantPrivilegeFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改权限',
			url : '${cxt}/role/grantPrivilege.action?id=' + id,
			buttons : [ {
				text : '修改',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url :  '${cxt}/role/getList.action',
			striped : true,//是否显示斑马线效果。
			rownumbers : true, //左边显示序号
			pagination : true, //是否分页
			singleSelect : true, //是否单选	
			idField : 'id',
			sortName : 'seq',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '角色名称',
				field : 'name',
				sortable : true
			} ] ],
			columns : [ [ {
				width : '150',
				title : '创建时间',
				field : 'createdatetime',
				sortable : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updatedatetime',
				sortable : true
			}, {
				width : '300',
				title : '资源描述',
				field : 'description'
			}, {
				width : '60',
				title : '排序',
				field : 'seq',
				hidden : true,
				sortable : true
			}, {
				title : '操作',
				field : 'action',
				width : '150',
				formatter : function(value, row) {
					var str = '';
					str += '<span style="color:blue;" onclick="editFun('+row.id+')">修改</span> '; 
					str += '<span style="color:blue;" onclick="grantPrivilegeFun('+row.id+')">授权</span> '; 
					str += '<span style="color:blue;" onclick="removeFun('+row.id+')">删除</span>'; 
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				//加载之前，启动loading
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(data) {
				//$('.iconImg').attr('src', sy.pixel_0);
				//加载之后，启动loading
				parent.$.messager.progress('close');
			}
		});
	});
	
function query(){
	//将表单中不为空的转成json对象
	var data = sy.serializeObject($('#searchForm'));
	/* console.log(data);
	var x = $('#searchForm').serialize();
	console.log(x); */
	grid.datagrid('load',data);
}
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="addFun();">添加</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'name':value});},prompt:'搜索角色名称'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>