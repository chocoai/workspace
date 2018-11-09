Ext.define('Ext.ux.custom.GlobalGridPanel', {
	extend : 'Ext.grid.Panel',
	alias : 'widget.globalgrid',
	// requires : [ 'Ext.ux.grid.FiltersFeature' ],
	xtype : 'cell-editing',
	initComponent : function() {
		var me = this;
		var singleId;

		var uniqueID = me.cName + (me.cId ? me.cId : '') + (me.myId ? me.myId : '');

		this.cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			clicksToEdit : 2
		});

		var tbarMenus = new Array();
		if (globalObject.haveActionMenu(me.cButtons, 'Add')) {
			tbarMenus.push({
				xtype : 'button',
				itemId : 'btnAdd',
				iconCls : 'icon-add',
				text : '添加',
				scope : this,
				// disabled : !globalObject.haveAction(me.cName + 'Add'),
				handler : me.onAddClick
			});
		}
		if (globalObject.haveActionMenu(me.cButtons, 'Import')) {
			tbarMenus.push({
				xtype : 'button',
				itemId : 'btnImport',
				iconCls : 'icon-excel',
				text : '导入',
				scope : this,
				// disabled : !globalObject.haveAction(me.cName + 'Add'),
				handler : me.onImportClick
			});
		}
		if (globalObject.haveActionMenu(me.cButtons, 'Export')) {
			tbarMenus.push({
				xtype : 'button',
				itemId : 'btnExport',
				iconCls : 'icon-export',
				text : '导出',
				scope : this,
				// disabled : !globalObject.haveAction(me.cName + 'Export'),
				handler : function() {
					me.onExportClick();
				}
			});
		}
		if (globalObject.haveActionMenu(me.cButtons, 'Delete')) {
			tbarMenus.push({
				xtype : 'button',
				itemId : 'btnDelete',
				iconCls : 'icon-delete',
				text : '删除',
				scope : this,
				disabled : true,
				handler : me.onDeleteClick
			});
		}

		if (tbarMenus.length == 0)
			me.hideTBar = true;
		this.ttoolbar = Ext.create('Ext.toolbar.Toolbar', {
			hidden : me.hideTBar || false,
			items : tbarMenus
		});

		Ext.apply(this, {
			stateful : me.cName ? true : false,
			stateId : me.cName ? (uniqueID + 'gird') : null,
			enableColumnMove : me.cName ? true : false,
			plugins : this.plugins,
			selModel : Ext.create('Ext.selection.CheckboxModel'),
			border : false,
			tbar : this.ttoolbar,
			bbar : me.hideBBar ? null : Ext.create('Ext.PagingToolbar', {
				store : me.getStore(),
				displayInfo : true
			}),
			listeners : {
				itemdblclick : function(dataview, record, item, index, e) {
					me.onViewClick();
				}
			}
		});
		this.getSelectionModel().on('selectionchange', function(sm, records) {
			if (me.down('#btnDelete'))
				me.down('#btnDelete').setDisabled(sm.getCount() == 0);
		});

		this.callParent(arguments);
	},
	createStore : function(config) {
		Ext.applyIf(this, config);
		return Ext.create('Ext.data.Store', {
			model : config.modelName,
			// autoDestroy: true,
			// autoLoad: true,
			remoteSort : true,
			pageSize : globalPageSize,
			proxy : {
				type : 'ajax',
				url : config.proxyUrl,
				extraParams : config.extraParams || null,
				reader : {
					type : 'json',
					root : 'data',
					totalProperty : 'totalRecord',
					successProperty : "success"
				}
			},
			sorters : [ {
				property : config.sortProperty,
				direction : config.sortDirection
			} ]
		});
	},
	getTabId : function() {
		return this.up('panel').getId();
	},
	onAddClick : function() {
	},
	onEditClick : function() {
	},
	onImportClick : function() {
		var FileRname = new Ext.form.TextField({
	        name : 'FileRname',
	        fieldLabel : '文件名',
	        allowBlank : false,
	        emptyText : '发布用于显示的文件名',
	        anchor:'95%'
	    });
	    var AddfileForm=new Ext.FormPanel(
	    {
	        name : 'AddfileForm',
	        frame : true,
	        labelWidth : 90,
	        url : appBaseUri + 'sys/order/getImport',
	        fileUpload : true,
	        width : 420,
	        autoDestroy : true,
	        bodyStyle : 'padding:0px 10px 0;',
	        items : [{
	                    xtype : 'filefield',
	                    emptyText : '选择上传文件',
	                    fieldLabel : '文件',
	                    name : 'upfile',
	                    buttonText : '',
	                    anchor : '95%',
	                    buttonCfg : {
	                        iconCls : 'icon_upfile'
	                    },
	                    listeners : {
	                        'fileselected' : function(fb, v) {
	                            var temp = v.replace(
	                                    /^.*(\.[^\.\?]*)\??.*$/, '$1');
	                            var temp1 = temp.toLocaleLowerCase();
	                            alert(temp1);
	                            if (allowfiletype.indexOf(temp1) == -1) {
	                                Ext.Msg.alert("错误","不允许选择该类型文件，请重新选择！");
	                                fb.setValue("");
	                                FileRname.setValue("");
	                            } else {
	                                FileRname.setValue(v.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1"));
	                            }
	                        }
	                    }
	                }]
	    });
	    var AddfileWin=new Ext.Window(
	    {
	        name : 'AddfileWin',
	        extend : 'Ext.window.Window',
	        layout : 'fit',
	        closeAction : 'close',
	        title : '上传文件',
	        buttonAlign : 'center',
	        resizable : false,
	        modal : true,
	        autoDestroy : true,
	        items : AddfileForm,
	        buttons :[{
	                    text : '保存',
	                    handler : function() {
	                        if (AddfileForm.getForm().isValid()) {
	                            Ext.MessageBox.show({
	                                title : '请稍等...',
	                                msg : '文件上传中...',
	                                progressText : '',
	                                width : 300,
	                                progress : true,
	                                closable : false,
	                                animEl : 'loding'
	                            });
	                            //var tblname=document.getElementById("upfile").value;
	                            AddfileForm.getForm().submit(
	                                    {
	                                        params: {
	                                            //tbl: tblname
	                                        },
	                                        success : function(form, action) {
	                                            var Result = action.result.flag;
	                                            if (Result != 0) {
	                                                
	                                                Ext.MessageBox.alert("提示",action.result.message);
	                                                AddfileWin.close();
	                                                var tabPanel = Ext.getCmp('centerTab');
	                                                SJSTgrid.getStore().reload();
	                                                tabPanel.setActiveTab(1);
	                                            } else if (Result == 0) {
	                                                Ext.MessageBox.alert("提示",action.result.message);
	                                                ds.load({
	                                                    params : {
	                                                        start : start,
	                                                        limit : limit
	                                                    }
	                                                });
	                                                AddfileForm.getForm().reset();
	                                            }
	                                        },
	                                        failure : function(form, action) {
	                                            Ext.MessageBox.alert("提示",
	                                                    "服务器请求错误,请稍后再试！");
	                                        }
	                                    })
	                        }
	                    }
	                }, {
	                    text : '重置',
	                    handler : function() {
	                        AddfileForm.getForm().reset();
	                    }
	                }, {
	                    text : '关闭',
	                    handler : function() {
	                        AddfileWin.close();
	                    }
	                } ]
	    });
	    AddfileWin.show();
	},
	onViewClick : function() {
	},
	onDeleteClick : function() {
		var me = this;
		globalObject.confirmTip('删除的记录不可恢复，继续吗？', function(btn) {
			if (btn == 'yes') {
				var s = me.getSelectionModel().getSelection();
				var ids = [];
				var idProperty = me.idProperty || 'id';
				for (var i = 0, r; r = s[i]; i++) {
					ids.push(r.get(idProperty));
				}
				Ext.Ajax.request({
					url : me.proxyDeleteUrl,
					params : {
						ids : ids.join(',') || singleId
					},
					success : function(response) {
						if (response.responseText != '') {
							var res = Ext.decode(response.responseText);
							if (res.success) {
								globalObject.msgTip('操作成功！');
								// Ext.example.msg('系统信息', '{0}', "操作成功！");
								me.getStore().reload();
							} else {
								globalObject.errTip('操作失败！' + res.msg);
							}
						}
					}
				});
			}
		});
	},
	onExportClick : function() {
		var me = this;
		var s = me.getSelectionModel().getSelection();
		var ids = [];
		var idProperty = me.idProperty || 'id';
		for (var i = 0, r; r = s[i]; i++) {
			ids.push(r.get(idProperty));
		}
		if (ids.length < 1) {
			globalObject.infoTip('请先选择导出的数据行！');
			return;
		}
		location.href = me.proxyExportUrl + "?ids=" + ids;
		/**
		Ext.Ajax.request({
			url : me.proxyExportUrl,
			method : 'POST',
			params : {
				ids : ids.join(',')
			},
			success : function(response) {
			}
		});
		**/
	}
});
