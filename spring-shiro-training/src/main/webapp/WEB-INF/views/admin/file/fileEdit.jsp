<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#fileEditForm').form({
            url : '${path }/file/edit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#fileEditForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="fileEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>上传者</td>
                    <td><input name="id" type="hidden"  value="${files.id}">
                    <input name="account" type="text" placeholder="请输入上传者名称" class="easyui-validatebox" data-options="required:true" value="${files.account}"></td>
                </tr>
                <tr>
                    <td>文件名</td>
                    <td> <input name="name"  class="easyui-validatebox" style="width: 140px; height: 29px;" required="required"  value="${files.name}"></td>
                </tr>

                <tr>
                    <td>分类</td>
                    <td>
                        <select name="type"   id="type"   class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'" >
                            <c:forEach var="item" items="${contents}" varStatus="v">
                            <option value="${item.code}"  <c:if test="${files.type.equals(item.code)}">selected="selected"</c:if>>${item.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>