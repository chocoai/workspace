Ext.define('Ext.app.Home',{ // 起始页
	extend : 'Ext.Panel',
	initComponent : function() {
        var me = this;
		Ext.apply(me,{
			autoScroll : true,
			bodyStyle:'background:#eaeaea;',
			layout: {
			    type: 'table',
			    columns: 4
			} ,
			items: [
			         { xtype: 'panel',width:256,height:80,margin:'20 10 0 20',
			        	 layout:'column',
			        	 items: [{
			        		  columnWidth: 0.6,
			        		  border: false,   
			        		  layout : 'form',
			        		  margin : '10 30 0 3',
			        		  items : [{
			        			  border: false,   
			        			  bodyStyle:'color:red;',
				        		  html: '今日来电：'
			        		  },{
			        			  border: false,
			        			  id : 'jrld',
			        			  margin : '0 0 0 30',
			        			  bodyStyle:'font-size:20px',
			        			  html: ''
			        		  }] 
			        	 },
		        		  Ext.create('Ext.Img', {
		        			    width : 64,
		        			    columnWidth: 0.4,
		        			    height: 64,
		        			    margin:'8 5 8 0',
		        			    src: 'http://www.sencha.com/img/20110215-feat-html5.png'
		        			})
			        	 ]
			         },
			         { xtype: 'panel',width:256,height:80,margin:'20 10 0 10' ,
			        	 layout:'column',
			        	 items: [{
			        		  columnWidth: 0.6,
			        		  border: false,   
			        		  layout : 'form',
			        		  margin : '10 30 0 3',
			        		  items : [{
			        			  border: false,   
			        			  bodyStyle:'color:red;',
				        		  html: '今日接听：'
			        		  },{
			        			  border: false,
			        			  margin : '0 0 0 30',
			        			  id : 'jrjt',
			        			  bodyStyle:'font-size:20px',
			        			  html: '0'
			        		  }] 
			        	 },
		        		  Ext.create('Ext.Img', {
		        			    width : 64,
		        			    columnWidth: 0.4,
		        			    height: 64,
		        			    margin:'8 5 8 0',
		        			    src: 'http://www.sencha.com/img/20110215-feat-html5.png'
		        			})
			        	 ]
			         },
			         { xtype: 'panel',width:256,height:80,margin:'20 10 0 10',
			        	 layout:'column',
			        	 items: [{
			        		  columnWidth: 0.6,
			        		  border: false,   
			        		  layout : 'form',
			        		  margin : '10 30 0 3',
			        		  items : [{
			        			  border: false,   
			        			  bodyStyle:'color:red;',
				        		  html: '待办工单：'
			        		  },{
			        			  border: false,
			        			  margin : '0 0 0 30',
			        			  id : 'dbgd',
			        			  bodyStyle:'font-size:20px',
			        			  html: ''
			        		  }] 
			        	 },
		        		  Ext.create('Ext.Img', {
		        			    width : 64,
		        			    columnWidth: 0.4,
		        			    height: 64,
		        			    margin:'8 5 8 0',
		        			    src: 'http://www.sencha.com/img/20110215-feat-html5.png'
		        			})
			        	 ]
			         },
			         { xtype: 'panel',width:256,height:80,margin:'20 10 0 10',
			        	 layout:'column',
			        	 items: [{
			        		  columnWidth: 0.6,
			        		  border: false,   
			        		  layout : 'form',
			        		  margin : '10 30 0 3',
			        		  items : [{
			        			  border: false,   
			        			  bodyStyle:'color:red;',
				        		  html: '已办工单：'
			        		  },{
			        			  border: false,
			        			  margin : '0 0 0 30',
			        			  id : 'ybgd',
			        			  bodyStyle:'font-size:20px',
			        			  html: ''
			        		  }] 
			        	 },
		        		  Ext.create('Ext.Img', {
		        			    width : 64,
		        			    columnWidth: 0.4,
		        			    height: 64,
		        			    margin:'8 5 8 0',
		        			    src: 'http://www.sencha.com/img/20110215-feat-html5.png'
		        			})
			        	 ]
			         },
			         { xtype: 'panel',colspan:2,margin:'20 10 0 20',height:240 ,
			        	 layout: 'fit',
			        	 items : [{
			        		    xtype : 'chart',
			        		    renderTo: Ext.getBody(),
			        		    animate: false,
			                    legend: {             
			                        position: 'top'   //图例
			                      },
			        		    store: Ext.create('Ext.data.JsonStore', {
			        			    fields: ['name', 'data1','data2'],
			        			    data: [
			        			        { 'name': '张三', 'data1':40,'data2':40},
			        			        { 'name': '李四','data1': 70,'data2': 70 },
			        			        { 'name': '王二', 'data1': 50,'data2': 70 },
			        			        { 'name': '王五','data1': 50,'data2': 70 },
			        			        { 'name': '赵六','data1': 28,'data2': 70 },
			        			        { 'name': '小明','data1': 120,'data2': 70 },
			        			        { 'name': '小李','data1': 64,'data2': 70 },
			        			        { 'name': '周五','data1': 40,'data2': 70 },
			        			        { 'name': '马二','data1':270,'data2': 70 }
			        			    ]
			        			}),
			        		    axes: [
			        		        {
			        		            type: 'Numeric',
			        		            position: 'left',
			        		            fields: ['data1','data2'],
			        		            label: {
			        		                renderer: Ext.util.Format.numberRenderer('0,0')
			        		            },
			        		            title: '本月工单数量',
			        		            grid: true,
			        		            minimum: 0
			        		        },
			        		        {
			        		            type: 'Category',
			        		            position: 'bottom',
			        		            fields: ['name']
			        		        }
			        		    ],
			        		    series: [
			        		        {
			        		            type: 'column',
			        		            axis: 'left',
			        		            highlight: true,
			        		            tips: {
			        		              trackMouse: true,
			        		              width: 140,
			        		              height: 28,
			        		              renderer: function(storeItem, item) {
			        		                this.setTitle(storeItem.get('name') + ': ' + storeItem.get('data1') + '份');
			        		              }
			        		            },
			        		            label: {
			        		              display: 'insideEnd',
			        		              'text-anchor': 'middle',
			        		                field: ['data1', 'data2'],
			        		                renderer: Ext.util.Format.numberRenderer('0'),
			        		                orientation: 'vertical',
			        		                color: '#333'
			        		            },
			        		            xField: 'name',
			        		            yField: ['data1', 'data2'],
			        		            title : ['指派','完成']
			        		        }
			        		    ]
			        		}
			        	 ]
			         },
			         { xtype: 'panel',colspan:2,margin:'20 10 0 10',height:240,
			        	 layout: 'fit',
			        	 items : [{
			        		    xtype : 'chart',
			        		    animate: false,
			        		    store: Ext.create('Ext.data.JsonStore', {
			        			    fields: ['name', 'data'],
			        			    data: [
			        			        { 'name': '2018-03-01',   'data':40},
			        			        { 'name': '03-02',  'data': 64 },
			        			        { 'name': '03-03',  'data': 40 },
			        			        { 'name': '03-04',  'data':270 },
			        			        { 'name': '03-11',   'data': 70 },
			        			        { 'name': '03-12', 'data': 50 },
			        			        { 'name': '03-13',  'data': 50 },
			        			        { 'name': '03-14',  'data': 28 },
			        			        { 'name': '03-15',  'data': 120 }
			
			        			    ]
			        			}),
			        		    axes: [
			        		        {
			        		            type: 'Numeric',
			        		            position: 'left',
			        		            fields: ['data'],
			        		            label: {
			        		                renderer: Ext.util.Format.numberRenderer('0,0')
			        		            },
			        		            title: '本月来电数量',
			        		            grid: true,
			        		            minimum: 0
			        		        },
			        		        {
			        		            type: 'Category',
			        		            position: 'bottom',
			        		            fields: ['name']
			        		        }
			        		    ],
			        		    series: [
			        		        {
			        		            type: 'line',
			        		            axis: 'left',
			        		            highlight: true,
			        		            tips: {
			        		              trackMouse: true,
			        		              width: 140,
			        		              height: 28,
			        		              renderer: function(storeItem, item) {
			        		                this.setTitle(storeItem.get('name') + ': ' + storeItem.get('data') + '个');
			        		              }
			        		            },
			        		            label: {
			        		              display: 'insideEnd',
			        		              'text-anchor': 'middle',
			        		                field: 'data',
			        		                renderer: Ext.util.Format.numberRenderer('0'),
			        		                orientation: 'vertical',
			        		                color: '#333'
			        		            },
			        		            xField: 'name',
			        		            yField: 'data'
			        		        }
			        		    ]
			        		}
			        	 ]
			         },
			         { xtype: 'panel',rowspan:2,margin:'20 10 0 20',height:160,
			        	 layout : 'fit',
			        	  items:[ {
			        		    xtype: 'chart',
			        		    id : 'pie1',
			        		    animate: true,
			        		    store: Ext.create('Ext.data.JsonStore', {
			        		        fields: ['name', 'data'],
			        		        data: [
			        		            { 'name': '接听',   'data':  82 },
			        		            { 'name': '拒接', 'data':  5 },
			        		            { 'name': '漏接',  'data':  12 },
			        		            { 'name': '转移',  'data': 3 }
			        		        ]
			        		    }),
			        		    theme: 'Category1',
			                    legend: {             
			                        position: 'right'   //图例
			                    },
			        		    series: [{
			        		        type: 'pie',
			        		        angleField: 'data',
			        		        showInLegend: true,
			        		        tips: {
			        		            trackMouse: true,
			        		            width: 140,
			        		            height: 28,
			        		            renderer: function(storeItem, item) {
			        		                var total = 0;
			        		                var store = Ext.getCmp('pie1').getStore();
			        		                store.each(function(rec) {
			        		                    total += rec.get('data');
			        		                });
			        		                this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data') / total * 100) + '%');
			        		            }
			        		        },
			        		        highlight: {
			        		            segment: {
			        		                margin: 20
			        		            }
			        		        },
			        		        label: {
			        		            field: 'name',
			        		            display: 'insideEnd',
			        		            contrast: true,
			        		            font: '12px Arial'
			        		        }
			        		    }]
			        		}]
			         },
			         { xtype: 'panel',rowspan:2,margin:'20 10 0 10',height:160,layout : 'fit',
			        	  items:[ {
			        		    xtype: 'chart',
			        		    id : 'pie2',
			        		    animate: true,
			        		    store: Ext.create('Ext.data.JsonStore', {
			        		        fields: ['name', 'data'],
			        		        data: [
			        		            { 'name': '待办',   'data':  82 },
			        		            { 'name': '进行中', 'data':  5 },
			        		            { 'name': '已完成',  'data':  12 }
			        		        ]
			        		    }),
			        		    theme: 'Category2',
			                    legend: {             
			                        position: 'right'   //图例
			                    },
			        		    series: [{
			        		        type: 'pie',
			        		        angleField: 'data',
			        		        showInLegend: true,
			        		        tips: {
			        		            trackMouse: true,
			        		            width: 140,
			        		            height: 28,
			        		            renderer: function(storeItem, item) {
			        		                var total = 0;
			        		                var store = Ext.getCmp('pie2').getStore();
			        		                store.each(function(rec) {
			        		                    total += rec.get('data');
			        		                });
			        		                this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data') / total * 100) + '%');
			        		            }
			        		        },
			        		        highlight: {
			        		            segment: {
			        		                margin: 20
			        		            }
			        		        },
			        		        label: {
			        		            field: 'name',
			        		            display: 'insideEnd',
			        		            contrast: true,
			        		            font: '12px Arial'
			        		        }
			        		    }]
			        		}]	 
			         },
			         { xtype: 'panel',rowspan:2,margin:'20 10 0 10',height:160,layout : 'fit',
			        	  items:[ {
			        		    xtype: 'chart',
			        		    id : 'pie3',
			        		    animate: true,
			        		    store: Ext.create('Ext.data.JsonStore', {
			        		        fields: ['name', 'data'],
			        		        data: [
			        		            { 'name': '待办',   'data':  82 },
			        		            { 'name': '进行中', 'data':  5 },
			        		            { 'name': '已完成',  'data':  12 }
			        		        ]
			        		    }),
			        		    theme: 'Category3',
			                    legend: {             
			                        position: 'right'   //图例
			                    },
			        		    series: [{
			        		        type: 'pie',
			        		        angleField: 'data',
			        		        showInLegend: true,
			        		        tips: {
			        		            trackMouse: true,
			        		            width: 140,
			        		            height: 28,
			        		            renderer: function(storeItem, item) {
			        		                var total = 0;
			        		                var store = Ext.getCmp('pie3').getStore();
			        		                store.each(function(rec) {
			        		                    total += rec.get('data');
			        		                });
			        		                this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data') / total * 100) + '%');
			        		            }
			        		        },
			        		        highlight: {
			        		            segment: {
			        		                margin: 20
			        		            }
			        		        },
			        		        label: {
			        		            field: 'name',
			        		            display: 'insideEnd',
			        		            contrast: true,
			        		            font: '12px Arial'
			        		        }
			        		    }]
			        		}]	  },
			         { xtype: 'panel',rowspan:2,margin:'20 10 0 10',height:160,layout : 'fit',
					        	  items:[ {
					        		    xtype: 'chart',
					        		    id : 'pie4',
					        		    animate: true,
					        		    store: Ext.create('Ext.data.JsonStore', {
					        		        fields: ['name', 'data'],
					        		        data: [
					        		            { 'name': '待办',   'data':  82 },
					        		            { 'name': '进行中', 'data':  5 },
					        		            { 'name': '已完成',  'data':  12 }
					        		        ]
					        		    }),
					        		    theme: 'Category4',
					                    legend: {             
					                        position: 'right'   //图例
					                    },
					        		    series: [{
					        		        type: 'pie',
					        		        angleField: 'data',
					        		        showInLegend: true,
					        		        tips: {
					        		            trackMouse: true,
					        		            width: 140,
					        		            height: 28,
					        		            renderer: function(storeItem, item) {
					        		                var total = 0;
					        		                var store = Ext.getCmp('pie4').getStore();
					        		                store.each(function(rec) {
					        		                    total += rec.get('data');
					        		                });
					        		                this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data') / total * 100) + '%');
					        		            }
					        		        },
					        		        highlight: {
					        		            segment: {
					        		                margin: 20
					        		            }
					        		        },
					        		        label: {
					        		            field: 'name',
					        		            display: 'insideEnd',
					        		            contrast: true,
					        		            font: '12px Arial'
					        		        }
					        		    }]
					        		}]	  }
			     ],
			isReLayout : false
		});
		me.callParent(arguments);
	},
	myfunction : function(){
		 Ext.getCmp('jrjt').body.update(455555);
	},
	afterRender : function(){
		Ext.Ajax.request({    
		       url: appBaseUri + 'sys/sysuser/getIndexData' ,
		       params:{ }, 
		       success: function(resp,opts) { 
	                 var respText = Ext.decode(resp.responseText); 
	                 Ext.getCmp('jrld').update(respText.jrld);
	                 Ext.getCmp('jrjt').update(respText.jrjt);
	                 Ext.getCmp('dbgd').update(respText.dbgd);
	                 Ext.getCmp('ybgd').update(respText.ybgd);
	           }, 
	           failure: function(resp,opts) { 
	                 Ext.Msg.alert('错误', '服务器内部错误'); 
	           }   
		});
		this.callParent(arguments);
	}

});
