<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/common/common.jsp" %>
<script type="text/javascript">
	var submitForm = function($dialog, $grid, $pjq) {
		//译者注：（1.3.4新增获取方式）
		var nodes = $('#tree').tree('getChecked', [ 'checked', 'indeterminate' ]);
		var ids = [];
		for (var i = 0; i < nodes.length; i++) {
			ids.push(nodes[i].id);
		}
		$.post('${cxt}/user/doGrantRole.action', {
			id : $(':input[name="id"]').val(),
			ids : ids.join(',')
		}, function(result) {
			if (result.success) {
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
			$pjq.messager.alert('提示', '修改成功！', 'info');
		}, 'json');
	};
	$(function() {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$('#tree').tree({
			url : '${cxt}/user/getAllRole.action',
			parentField : 'pid',
			checkbox : true,
			formatter : function(node) {
				return node.name;
			},
			onLoadSuccess : function(node, data) {
				//回显用户已有角色
				$.post('${cxt}/user/getRoleByUserId.action', {
					id : $(':input[name="id"]').val()
				}, function(result) {
					if (result) {
						for (var i = 0; i < result.length; i++) {
							//根据id查找树节点
							var node = $('#tree').tree('find', result[i].id);
							if (node) {
								//如果这个节点是叶子节点
								var isLeaf = $('#tree').tree('isLeaf', node.target);
								//如果是，将节点选中
								if (isLeaf) {
									$('#tree').tree('check', node.target);
								}
							}
						}
					}
					parent.$.messager.progress('close');
				}, 'json');
			}
		});
	});
</script>
</head>
<body>
	<input name="id" value="${param.id }" readonly="readonly" type="hidden" />
	<fieldset>
		<legend>所属角色</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>