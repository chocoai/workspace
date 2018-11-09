// 用户管理
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();

	Ext.define('App.OldManWindow', {
		extend : 'Ext.window.Window',
		constructor : function(config) {
			config = config || {};
			Ext.apply(config, {
				title : '表单信息',
				width : 350,
				height : 280,
				bodyPadding : '10 5',
				modal : true,
				layout : 'fit',
				items : [ {
					xtype : 'form',
					frame :true,
					fieldDefaults : {
						labelAlign : 'left',
						labelWidth : 90,
						anchor : '100%'
					},
					items : [{
						name : "cmd",
						xtype : "hidden",
						value : 'new'
					}, {
						xtype : 'hiddenfield',
						name : 'id'
					}, {
						xtype : 'textfield',
						name : 'name',
						fieldLabel : '名称',
						allowBlank : false,
						maxLength : 30
					}, {
						xtype : 'textfield',
						name : 'tel',
						fieldLabel : '电话',
						allowBlank : false,
						maxLength : 32
					}, {
						xtype : 'textfield',
						name : 'area',
						fieldLabel : '区域',
						maxLength : 30
					},  {
						xtype : 'combobox',
						fieldLabel : '类别',
						name : 'type',
						store : Ext.create('Ext.data.Store',{
	                    	fields: ['value', 'text'],
	                    	data : [{'value':'1','text':'优秀'},{'value':'2','text':'良好'},{'value':'3','text':'一般'},{'value':'4','text':'差'}]
	                    }),
						valueField : 'value',
						displayField : 'text',
						emptyText : '请选择...',
						allowBlank : false,
						editable : false
					}, {
						xtype : 'textfield',
						name : 'createtime',
						hidden :true,
						fieldLabel : '创建时间',
						maxLength : 30
					} ],
					buttons : [ '->', {
						id : 'OldManwindow-save',
						text : '保存',
						iconCls : 'icon-save',
						width : 80,
						handler : function(btn, eventObj) {
							var window = btn.up('window');
							var form = window.down('form').getForm();
							if (form.isValid()) {
								window.getEl().mask('数据保存中，请稍候...');
								var vals = form.getValues();
								Ext.Ajax.request({
									url : appBaseUri + 'sys/test/saveTestDemo',
									params : {
										cmd : vals['cmd'],
										id : vals['id'],
										name : vals['name'],
										tel : vals['tel'],
										type : vals['type'],
										area : vals['area']
									},
									method : "POST",
									success : function(response) {
										if (response.responseText != '') {
											var res = Ext.JSON.decode(response.responseText);
											if (res.success) {
												globalObject.msgTip('操作成功！');
												Ext.getCmp('OldMangrid').getStore().reload();
											} else {
												globalObject.errTip('数据已存在，请输入唯一值！');
											}
										}
									},
									failure : function(response) {
										globalObject.errTip('操作失败！');
									}
								});
								window.getEl().unmask();
								window.close();
							}
						}
					}, {
						id : 'OldManwindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'OldManwindow-accept',
						text : '确定',
						hidden : true,
						iconCls : 'icon-accept',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, '->' ]
				} ]
			});
			App.OldManWindow.superclass.constructor.call(this, config);
		}
	});
	
	//查询表单
	Ext.define('Yhcrt.app.baseData.OldManSearchForm', {
		extend : 'Ext.form.Panel',
		region:"north",
		border : false,
		layout : "form",
		id : 'searchForm',
		frame : true,
		padding : "0 0 0 0",
		items:[{
			xtype:"fieldset",
			title:"查询设置",
			layout:"column",
			defaults:{
				xtype:"container",
				padding : "0 0 5 0",
				layout:"fit"
			},
			items:[{
					columnWidth:.22,
					items:[{
						xtype:"textfield",
						anchor:'92%', 
						labelWidth: 65,	
						labelAlign:"right",
						name:"name",
						fieldLabel:"名称"
					}]
				},{
					columnWidth:.22,
					items:[{
						xtype:"textfield",
						anchor:'92%', 
						labelWidth: 65,	
						labelAlign:"right",
						name:"tel",
						fieldLabel:"电话"
					}]
				},{
					columnWidth:.22,
					items:[{
						xtype:"textfield",
						anchor:'92%', 
						labelWidth: 65,	
						labelAlign:"right",
						name:"area",
						fieldLabel:"区域"
					}]
				},{
					columnWidth:.22,
					items:[{
						fieldLabel:"类别",
	                 	xtype : 'combo',
	     				mode: 'local',
	     				name : 'type',
	     				anchor:'92%', 
	    				labelWidth: 65,	
	    				labelAlign:"right",
	     				triggerAction: 'all',
	     				valueField :'value',
	     				displayField: 'text',
	     				emptyText: '请选择',
	                     store :  new Ext.data.SimpleStore({
	                     	fields: ['value', 'text'],
	                     	data : [['1','优秀'],['2','良好'],['3','一般'],['3','差']]
	                     }),
	                     hiddenName:'type'
					}]
				},{
					columnWidth:.12,
					layout : 'form',
					items:[{
						xtype:"button",
						iconCls:"btn-list",
						margin : '0 0 0 20',
						text:"查询",
						handler:function(){
							store.load();
						}
					}]
				}]
	
		}]
	}); //Yhcrt.app.baseData.OldManManagement

	var store ;
	Ext.define('Yhcrt.app.baseData.OldManGrid', {
		extend : 'Ext.ux.custom.GlobalGridPanel',
		region : 'center',
		initComponent : function() {
			var me = this;
			Ext.define('ModelList', {
				extend : 'Ext.data.Model',
				idProperty : 'id',
				fields : [ {
					name : 'id',
					type : 'long'
				}, 'name', 'tel', {name:'type',type: 'string'}, 'area', 'createtime']
			});

			store = me.createStore({
				modelName : 'ModelList',
				proxyUrl : appBaseUri + 'sys/test/getTestDemoList',
				proxyDeleteUrl : appBaseUri + 'sys/test/deleteTestDemo',
				extraParams : me.extraParams
			});
			var from = Ext.getCmp('searchForm').getForm();
			store.on('beforeload',function(){
				 Ext.apply(   
				  this.proxy,   
				  {   
					 extraParams:{
					'name' : from.findField('name').getValue(),
					'tel' : from.findField('tel').getValue(),
					'type' : from.findField('type').getValue(),
					'area' : from.findField('area').getValue() }
				   }   
				 );   
			});
		    
			var columns = [ {
				text : "ID",
				dataIndex : 'id',
				width : '5%'
			}, {
				text : "名称",
				dataIndex : 'name',
				width : '13%'
			}, {
				text : "电话",
				dataIndex : 'tel',
				width : '10%'
			}, {
				text : "类型",
				dataIndex : 'type',
				renderer: function(value){
			        if (value == 1) {
			            return '优秀';
			        }else if (value == 2) {
			            return '良好';
			        }if (value == 3) {
			            return '一般';
			        }if (value == 4) {
			            return '差';
			        }
			        return value ;
			    },
				width : '14%'
			}, {
				text : "区域",
				dataIndex : 'area',
				width : '15%'
			}, {
				text : "创建时间",
				dataIndex : 'createtime',
				width : '19%'
			}, {
				xtype : 'actioncolumn',
				width : '8%',
				items : [ {
					iconCls : 'icon-view',
					xtype : 'button',
					tooltip : '查看',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'View'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.OldManWindow({
							hidden : true
						});
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);
						form.findField('name').setReadOnly(true);
				//		form.findField('type').hide();
						form.findField('area').setReadOnly(true);
						form.findField('createtime').show();
						form.findField('createtime').setReadOnly(true);
						form.findField('tel').setReadOnly(true);
						Ext.getCmp('OldManwindow-save').hide();
						Ext.getCmp('OldManwindow-cancel').hide();
						Ext.getCmp('OldManwindow-accept').show();
						win.show();
					}
				}, {
					iconCls : 'edit',
					tooltip : '修改',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Edit'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.OldManWindow({
							hidden : true
						});
						win.setHeight(250);
						var form = win.down('form').getForm();
						form.loadRecord(gridRecord);
						form.findField("cmd").setValue("edit");
						win.show();
					}
				}, {
					iconCls : 'icon-delete',
					tooltip : '删除',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Delete'),
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						singleId = entity.get('id');
						me.onDeleteClick();
					}
				} ]
			} ];

			Ext.apply(this, {
				id : 'OldMangrid',
				store : store,
				columns : columns
			});
			store.loadPage(1);
			this.callParent(arguments);
		},
		onAddClick : function() {
			new App.OldManWindow().show();
		},
		onViewClick : function() {
			var grid = Ext.getCmp("OldMangrid");
			var id = grid.getSelectionModel().getSelection()[0].get('id');
			var gridRecord = grid.getStore().findRecord('id', id);
			var win = new App.OldManWindow({
				hidden : true,
				height : 230
			});
			var form = win.down('form').getForm();
			form.loadRecord(gridRecord);
			form.findField('name').setReadOnly(true);
			form.findField('area').setReadOnly(true);
			form.findField('createtime').show();
			form.findField('createtime').setReadOnly(true);
			form.findField('tel').setReadOnly(true);
			Ext.getCmp('OldManwindow-save').hide();
			Ext.getCmp('OldManwindow-cancel').hide();
			Ext.getCmp('OldManwindow-accept').show();
			win.show();
		}
	});
	

	
	Ext.define('Yhcrt.app.baseData.OldManManagement', {
		extend : 'Ext.panel.Panel',
		layout : 'border',
		constructor: function (config) {
			  this.config = config;
              this.callParent();
        },
		initComponent : function() {
			var me = this;
			me.items = [Ext.create('Yhcrt.app.baseData.OldManSearchForm',me.config),Ext.create('Yhcrt.app.baseData.OldManGrid',me.config)];
			me.callParent(arguments);
		}
	});
	
});