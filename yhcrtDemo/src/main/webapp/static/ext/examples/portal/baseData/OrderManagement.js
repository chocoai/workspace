// 用户管理
Ext.onReady(function() {
	Ext.tip.QuickTipManager.init();

	Ext.define('App.OrderManWindow', {
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
	                    xtype : 'filefield',
	                    emptyText : '选择上传文件',
	                    fieldLabel : '文件',
	                    name : 'name',
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
					}, {
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
						id : 'OrderManWindow-save',
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
									params : vals,
									method : "POST",
									success : function(response) {
										if (response.responseText != '') {
											var res = Ext.JSON.decode(response.responseText);
											if (res.success) {
												globalObject.msgTip('操作成功！');
												Ext.getCmp('OrderGrid').getStore().reload();
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
						id : 'OrderManWindow-cancel',
						text : '取消',
						iconCls : 'icon-cancel',
						width : 80,
						handler : function() {
							this.up('window').close();
						}
					}, {
						id : 'OrderManWindow-accept',
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
			App.OrderManWindow.superclass.constructor.call(this, config);
		}
	});
	
	//查询表单
	Ext.define('Yhcrt.app.baseData.OrderManSearchForm', {
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
						name:"cext1",
						fieldLabel:"工单编号"
					}]
				},{
					columnWidth:.22,
					items:[{
						xtype:"textfield",
						anchor:'92%', 
						labelWidth: 65,	
						labelAlign:"right",
						name:"memberId",
						fieldLabel:"会员ID"
					}]
				},{
					columnWidth:.22,
					items:[{
						xtype:"textfield",
						anchor:'92%', 
						labelWidth: 65,	
						labelAlign:"right",
						name:"recipient",
						fieldLabel:"收货人"
					}]
				},{
					columnWidth:.22,
					items:[{
						/*fieldLabel:"电话号码",
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
	                     hiddenName:'type'*/
						xtype:"textfield",
						anchor:'92%', 
						labelWidth: 65,	
						labelAlign:"right",
						name:"phoneNum",
						fieldLabel:"联系电话"
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
	Ext.define('Yhcrt.app.baseData.OrderGrid', {
		extend : 'Ext.ux.custom.GlobalGridPanel',
		region : 'center',
		initComponent : function() {
			var me = this;
			Ext.define('ModelList', {
				extend : 'Ext.data.Model',
				idProperty : 'id',
				fields : [ 
				       { name : 'orderId'}, 
				       { name : 'memberId'}, 
				       { name : 'orderAmount'}, 
				       { name : 'freight'}, 
				       { name : 'discounts'}, 
				       { name : 'actualPayment'},
				       { name : 'recipient'}, 
				       { name : 'phoneNum'}, 
				       { name : 'address'}, 
				       { name : 'addressId'}, 
				       { name : 'orderStatus'}, 
				       { name : 'createTime'}, 
				       { name : 'payType'}, 
				       { name : 'payAccount'}, 
				       { name : 'payValue'}, 
				       { name : 'payTime'}, 
				       { name : 'logistics'}, 
				       { name : 'waybill'}, 
				       { name : 'cext1'}, 
				       { name : 'cext2'}, 
				       { name : 'cext3'}, 
				       { name : 'iext1'}, 
				       { name : 'iext2'}, 
				       { name : 'dext1'}, 
				       { name : 'dext2'}, 
				       ]
			});

			store = me.createStore({
				modelName : 'ModelList',
				proxyUrl : appBaseUri + 'sys/order/getOrders',
				proxyDeleteUrl : appBaseUri + 'sys/test/deleteTestDemo',
				proxyExportUrl : appBaseUri + 'sys/order/getExport',
				extraParams : me.extraParams,
				idProperty :"orderId"
			});
			var from = Ext.getCmp('searchForm').getForm();
			store.on('beforeload',function(){
				 Ext.apply(   
				  this.proxy,   
				  {   
					 extraParams:{
					'cext1' : from.findField('cext1').getValue(),
					'memberId' : from.findField('memberId').getValue(),
					'recipient' : from.findField('recipient').getValue(),
					'phoneNum' : from.findField('phoneNum').getValue() }
				   }   
				 );   
			});
		    
			var columns = [ {
				text : "主键",
				dataIndex : 'orderId',
				width : '5%'
			}, {
				text : "会员ID",
				dataIndex : 'memberId',
				width : '5%'
			}, {
				text : "订单总价",
				dataIndex : 'orderAmount',
				width : '5%'
			}, {
				text : "运费",
				dataIndex : 'freight',
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
				width : '5%'
			}, {
				text : "优惠额",
				dataIndex : 'discounts',
				width : '5%'
			}, {
				text : "实际付款",
				dataIndex : 'actualPayment',
				width : '5%'
			}, {
				text : "收货人",
				dataIndex : 'recipient',
				width : '5%'
			}, {
				text : "联系电话",
				dataIndex : 'phoneNum',
				width : '5%'
			}, {
				text : "收货地址",
				dataIndex : 'address',
				width : '5%'
			}, {
				text : "收货地址ID",
				dataIndex : 'addressId',
				width : '5%'
			}, {
				text : "订单状态",
				dataIndex : 'orderStatus',
				width : '5%'
			}, {
				text : "下单时间",
				dataIndex : 'createTime',
				width : '5%'
			}, {
				text : "支付类型",
				dataIndex : 'payType',
				width : '5%'
			}, {
				text : "交易号",
				dataIndex : 'payAccount',
				width : '5%'
			}, {
				text : "支付金额",
				dataIndex : 'payValue',
				width : '5%'
			}, {
				text : "支付时间",
				dataIndex : 'payTime',
				width : '5%'
			}, {
				text : "物流公司",
				dataIndex : 'logistics',
				width : '5%'
			}, {
				text : "货运单号",
				dataIndex : 'waybill',
				width : '5%'
			}, {
				text : "工单编号",
				dataIndex : 'cext1',
				width : '5%'
			}, {
				text : "与支付宝,微信交易号",
				dataIndex : 'cext2',
				width : '5%'
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
						var win = new App.OrderManWindow({
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
						Ext.getCmp('OrderManWindow-save').hide();
						Ext.getCmp('OrderManWindow-cancel').hide();
						Ext.getCmp('OrderManWindow-accept').show();
						win.show();
					}
				}, {
					iconCls : 'edit',
					tooltip : '修改',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Edit'),
					handler : function(grid, rowIndex, colIndex) {
						var gridRecord = grid.getStore().getAt(rowIndex);
						var win = new App.OrderManWindow({
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
				} , {
					iconCls : 'icon-excel',
					tooltip : '导入',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Import'),
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						singleId = entity.get('id');
						me.onImportClick();
					}
				} , {
					iconCls : 'icon-export',
					tooltip : '导出',
					disabled : !globalObject.haveActionMenu(me.cButtons, 'Export'),
					handler : function(grid, rowIndex, colIndex) {
						var entity = grid.getStore().getAt(rowIndex);
						singleId = entity.get('orderId');
						me.onExportClick();
					}
				}]
			} ];

			Ext.apply(this, {
				id : 'OrderGrid',
				store : store,
				columns : columns
			});
			store.loadPage(1);
			this.callParent(arguments);
		},
		onAddClick : function() {
			new App.OrderManWindow().show();
		},
		onViewClick : function() {
			var grid = Ext.getCmp("OrderGrid");
			var id = grid.getSelectionModel().getSelection()[0].get('id');
			var gridRecord = grid.getStore().findRecord('id', id);
			var win = new App.OrderManWindow({
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
			Ext.getCmp('OrderManWindow-save').hide();
			Ext.getCmp('OrderManWindow-cancel').hide();
			Ext.getCmp('OrderManWindow-accept').show();
			win.show();
		}
	});
	

	
	Ext.define('Yhcrt.app.baseData.OrderManagement', {
		extend : 'Ext.panel.Panel',
		layout : 'border',
		constructor: function (config) {
			  this.config = config;
              this.callParent();
        },
		initComponent : function() {
			var me = this;
			me.items = [Ext.create('Yhcrt.app.baseData.OrderManSearchForm',me.config),Ext.create('Yhcrt.app.baseData.OrderGrid',me.config)];
			me.callParent(arguments);
		}
	});
	
});