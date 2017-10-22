
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ include file="/commons/global.jsp" %>
    <script type="text/javascript">
        var FilesTypeDateGrid;
        $(function() {
            FilesTypeDateGrid = $('#FilesTypeDateGrid').datagrid({
                url : '${path }/filetype/dataGrid',
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
                } , {
                    width : '80',
                    title : '编码',
                    field : 'code',
                    sortable : true
                }, {
                    width : '80',
                    title : '类别',
                    field : 'dmlb',
                    sortable : true
                } , {
                    width : '120',
                    title : '创建时间',
                    field : 'creattime'
                }, {
                    field : 'action',
                    title : '操作',
                    width : 200,
                    formatter : function(value, row, index) {
                        var str = '';
                        <shiro:hasPermission name="/filetype/edit">
                        str += '&nbsp;&nbsp;&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editFileTypeFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/filetype/delete">
                        str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                        str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteFilesTypeFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                        return str;
                    }
                } ] ],
                onLoadSuccess:function(data){

                    $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                    $('.role-easyui-linkbutton-del').linkbutton({text:'删除'});
                },
                toolbar : '#filetypeToolbar'
            });
        });

        function addFilestypeFun() {
            parent.$.modalDialog({
                title : '添加',
                width : 500,
                height : 300,
                href : '${path }/filetype/addPage',
                buttons : [ {
                    text : '确定',
                    handler : function() {
                        parent.$.modalDialog.openner_dataGrid = FilesTypeDateGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#fileTypeAddForm');
                        f.submit();
                    }
                } ]
            });
        }


        function searchFileTypeFun() {
            FilesTypeDateGrid.datagrid('load', $.serializeObject($('#searchFileTypeForm')));
        }

        function UrlFile(url) {
           window.open(url);
        }

        function editFileTypeFun(id) {
            if (id == undefined) {
                var rows = FilesTypeDateGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {
                FilesTypeDateGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }
            parent.$.modalDialog({
                title : '编辑',
                width : 500,
                height : 300,
                href : '${path }/filetype/editPage?id=' + id,
                buttons : [ {
                    text : '确定',
                    handler : function() {
                        parent.$.modalDialog.openner_dataGrid = FilesTypeDateGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#filetypeEditForm');
                        f.submit();
                    }
                } ]
            });
        }

        function deleteFilesTypeFun(id) {
            if (id == undefined) {//点击右键菜单才会触发这个
                var rows = FilesTypeDateGrid.datagrid('getSelections');
                id = rows[0].id;
            } else {//点击操作里面的删除图标会触发这个
                FilesTypeDateGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }
            parent.$.messager.confirm('询问', '您是否要删除当前项目？', function(b) {
                if (b) {
                    progressLoad();
                    $.post('${path }/filetype/delete', {
                        id : id
                    }, function(result) {
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            FilesTypeDateGrid.datagrid('reload');
                        }
                        progressClose();
                    }, 'JSON');
                }
            });
        }


    </script>
    <div class="easyui-layout" data-options="fit:true,border:false">
      <%--  <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
            <form id="searchFileForm">
                <table>
                    <tr>
                        <th> 名称:</th>
                        <td><input name="name" placeholder="请输入名称"/></td>
                        <th> 编码:</th>
                        <td><input name="name" placeholder="请输入编码"/></td>
                        <th>类别：</th>
                        <td><input name="account" placeholder="请输入类别（英文）"/>
                            <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchFileTypeFun();">查询</a>
                            <shiro:hasPermission name="/filetype/add">
                                <a onclick="addFileFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
                            </shiro:hasPermission>
                        </td>
                    </tr>
                </table>
            </form>
        </div>--%>
        <div data-options="region:'center',fit:true,border:false">
            <table id="FilesTypeDateGrid" data-options="fit:true,border:false"></table>
        </div>
    </div>
</div>
    <div id="filetypeToolbar" style="display: none;">
            <shiro:hasPermission name="/filetype/add">
            <a onclick="addFilestypeFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
        </shiro:hasPermission>
    </div>

