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
			title : '添加用户信息',
			url :  '${cxt}/user/userAction!editUI.action',
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
			url : '${cxt}/user/userAction!editUI.action?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '编辑用户信息',
			url : '${cxt}/user/userAction!editUI.action?id=' + id,
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
				$.post('${cxt}/user/userAction!delete.action', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var grantRoleFun = function(id) {
		var dialog = parent.sy.modalDialog({
			title : '修改角色',
			url : '${cxt}/user/userAction!grantRole.action?id=' + id,
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
			url :  '${cxt}/user/userAction!getList.action',
			striped : true,//是否显示斑马线效果。
			rownumbers : true, //左边显示序号
			pagination : true, //是否分页
			singleSelect : true, //是否单选	
			idField : 'id',
			sortName : 'createdatetime', //排序的字段，会传递一个参数到后台， sort=createdatetime
			sortOrder : 'desc',   //排序是升序还是降序，会传递一个参数到后台，order=desc
			pageSize : 20,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			//冻结列，左边固定不动
			frozenColumns : [ [ {
				width : '100',
				title : '登录名',
				field : 'loginname',
				sortable : true
			}, {
				width : '80',
				title : '姓名',
				field : 'name',
				sortable : true
			} ] ],
			//普通列
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
				width : '50',
				title : '性别',
				field : 'sex',
				sortable : true,
				formatter : function(value, row, index) {
					switch (value) {
					case '0':
						return '女';
					case '1':
						return '男';
					}
				}
			}, {
				width : '50',
				title : '年龄',
				field : 'age',
				hidden : true
			}, {
				width : '250',
				title : '照片',
				field : 'photo',
				formatter : function(value, row) {
					if(value){
						return value;
						//return sy.formatString('<span title="{0}">{1}</span>', value, value);
					}
				}
			}, {
				title : '操作',
				field : 'action',
				width : '200',
				formatter : function(value, row) {
					var str = '';
					str += '<span style="color:blue;" onclick="editFun('+row.id+')">修改</span> '; 
					str += '<span style="color:blue;" onclick="grantRoleFun('+row.id+')">添加角色</span> '; 
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
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>登录名</td>
								<td><input name="loginname" style="width: 80px;" /></td>
								<td>姓名</td>
								<td><input name="name" style="width: 80px;" /></td>
								<td>性别</td>
								<td>
									<select name="sex" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option>
									</select>
								</td>
								<td>创建时间</td>
								<td><input name="startcreatedatetime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="endcreatedatetime" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="query()">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick="javascript:alert('未实现')">导入</a></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick="javascript:alert('未实现')">导出</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>