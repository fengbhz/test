
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/commons/global.jsp" %>
    <script type="text/javascript">
        var fileDateGrid;
        $(function() {
            fileDateGrid = $('#fileDateGrid').datagrid({
                url : '${path }/file/dataGrid',
                striped : true,
                rownumbers : true,
                pagination : true,
                singleSelect : true,
                idField : 'id',
                sortName : 'id',
                sortOrder : 'asc',
                pageSize : 20,
                pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
                frozenColumns : [ [  {
                    width : '80',
                    title : '名称',
                    field : 'name',
                    sortable : true
                } ,  {
                    width : '80',
                    title : '分类',
                    field : 'types',
                    sortable : true
                },{
                    width : '80',
                    title : '大小',
                    field : 'filesize',
                    sortable : true
                }, {
                    width : '500',
                    title : '路径',
                    field : 'url',
                    formatter : function(value, row, index) {
                            var str="";
                             str += $.formatString('<a href="javascript:void(0)"  onclick="UrlFile(\''+value+'\')"; >'+(null==value?"":value)+'</a> ');
                             return str;
                    }
                } , {
                    width : '60',
                    title : '作者',
                    field : 'account'
                }, {
                    field : 'action',
                    title : '操作',
                    width : 200,
                    formatter : function(value, row, index) {
                        var str = '';
                        <shiro:hasPermission name="/file/edit">
                        str += '&nbsp;&nbsp;&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editFileFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/file/delete">
                        str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteRoleFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                        return str;
                    }
                } ] ],
                onLoadSuccess:function(data){

                    $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                    $('.role-easyui-linkbutton-del').linkbutton({text:'删除'});
                },
                toolbar : '#fileToolbar'
            });
        });

        function addFileFun() {
            parent.$.modalDialog({
                title : '添加',
                width : 500,
                height : 300,
                href : '${path }/file/addPage',
                buttons : [ {
                    text : '确定',
                    handler : function() {
                        parent.$.modalDialog.openner_dataGrid = fileDateGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#fileAddForm');
                        f.submit();
                    }
                } ]
            });
        }


        function searchUserFun() {
            fileDateGrid.datagrid('load', $.serializeObject($('#searchFileForm')));
        }

        function UrlFile(url) {
           window.open(url);
        }

        function editFileFun(id) {
            if (id == undefined) {
                var rows = fileDateGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {
                fileDateGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }
            parent.$.modalDialog({
                title : '编辑',
                width : 500,
                height : 300,
                href : '${path }/file/editPage?id=' + id,
                buttons : [ {
                    text : '确定',
                    handler : function() {
                        parent.$.modalDialog.openner_dataGrid = fileDateGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#fileEditForm');
                        f.submit();
                    }
                } ]
            });
        }

        function deleteRoleFun(id) {
            if (id == undefined) {//点击右键菜单才会触发这个
                var rows = fileDateGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {//点击操作里面的删除图标会触发这个
                fileDateGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }
            parent.$.messager.confirm('询问', '您是否要删除当前项目？', function(b) {
                if (b) {
                    progressLoad();
                    $.post('${path }/file/delete', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            fileDateGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                }
            });
        }


    </script>
    <div class="easyui-layout" data-options="fit:true,border:false">
        <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
            <form id="searchFileForm">
                <table>
                    <tr>
                        <th> 文件名称:</th>
                        <td><input name="name" placeholder="请输入文件名称"/></td>
                        <th>作者：</th>
                        <td><input name="account" placeholder="请输入作者名称"/>
                        <th>分类：</th>
                        <td>  <select name="type"   id="type"   class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'" >
                            <option value="" >--请选择--</option>
                            <c:forEach var="item" items="${contents}" varStatus="v">
                                <option value="${item.code}" >${item.name}</option>
                            </c:forEach>
                        </select>

                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchUserFun();">查询</a>
                            <shiro:hasPermission name="/file/add">
                                <a onclick="addFileFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
                            </shiro:hasPermission>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div data-options="region:'center',fit:true,border:false">
            <table id="fileDateGrid" data-options="fit:true,border:false"></table>
        </div>
    </div>
   <%-- <div id="fileToolbar" style="display: none;">
        <shiro:hasPermission name="/file/add">
            <a onclick="addFileFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
        </shiro:hasPermission>

    </div>--%>
</div>


