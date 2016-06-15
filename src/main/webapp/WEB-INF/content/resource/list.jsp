<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
	var grid;
	var addFun = function() {
		var dialog = parent.sy.modalDialog({
			title : '添加资源信息',
			url : '${cxt}/resource/resourceAction!editUI.action',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '查看资源信息',
			url : '${cxt}/resource/resourceAction!editUI.action?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑资源信息',
			url : '${cxt}/resource/resourceAction!editUI.action?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post('${cxt}/resource/resourceAction!delete.action', {
					id : id
				}, function() {
					grid.treegrid('reload');
					parent.mainMenu.tree('reload');
				}, 'json');
			}
		});
	};
	
	//展开
	var redoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('expandAll', node.id);
		} else {
			grid.treegrid('expandAll');
		}
	};
	
	//折叠
	var undoFun = function() {
		var node = grid.treegrid('getSelected');
		if (node) {
			grid.treegrid('collapseAll', node.id);
		} else {
			grid.treegrid('collapseAll');
		}
	};
	$(function() {
		grid = $('#grid').treegrid({
			title : '',
			url : '${cxt}/resource/resourceAction!getResourceTree.action',
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			rownumbers : true,
			pagination : false,
			sortName : 'seq',
			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '200',
				title : '资源名称',
				field : 'name'
			} ] ],
			columns : [ [ {
				width : '200',
				title : '图标名称',
				field : 'iconCls'
			}, {
				width : '200',
				title : '资源路径',
				field : 'url',
				formatter : function(value, row) {
					if(value){
						return '<span title="'+value+'">'+value+'</span>';
					}
				}
			}, {
				width : '60',
				title : '资源类型',
				field : 'resourcetype',
				formatter : function(value, row) {
					// 获取关联对象的值
					return value?value.name:"";
				}
			}, {
				width : '150',
				title : '创建时间',
				field : 'createdatetime',
				hidden : true
			}, {
				width : '150',
				title : '修改时间',
				field : 'updatedatetime',
				hidden : true
			}, {
				width : '200',
				title : '资源描述',
				field : 'description',
				formatter : function(value, row) {
					if(value){
						return '<span title="'+value+'">'+value+'</span>';
					}
				}
			}, {
				width : '80',
				title : '排序',
				field : 'seq',
				hidden : true
			}, {
				width : '80',
				title : '目标',
				field : 'target'
			}, {
				title : '操作',
				field : 'action',
				width : '60',
				formatter : function(value, row) {
					var str = '';
					str += '<span style="color:blue;" onclick="editFun('+row.id+')">修改</span> '; 
					str += '<span style="color:blue;" onclick="removeFun('+row.id+')">删除</span>'; 
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(row, param) {
				parent.$.messager.progress({
					text : '数据加载中....'
				});
			},
			onLoadSuccess : function(row, data) {
				//$('.iconImg').attr('src', sy.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>