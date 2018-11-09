/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/

// google code prettify: http://google-code-prettify.googlecode.com/
// http://google-code-prettify.googlecode.com/

KindEditor.plugin('code', function(K) {
	var self = this, name = 'code';
	self.clickToolbar(name, function() {
		var lang = self.lang(name + '.'),
			html = ['<div style="padding:10px 20px;">',
				'<div class="ke-dialog-row">',
				'<select class="ke-code-type">',
				'<option value="java">Java</option>',
				'<option value="html">Html</option>',
				'<option value="js">JavaScript</option>',
				'<option value="css">CSS</option>',
				'<option value="sql">SQL</option>',
				'<option value="xml">XML</option>',
				'<option value="cpp">C/C++/Objective-C</option>',
				'<option value="c#">C#</option>',
				'<option value="ruby">Ruby</option>',
				'<option value="php">PHP</option>',
				'<option value="perl">Perl</option>',
				'<option value="python">Python</option>',
				'<option value="vb">ASP/Basic</option>',
				'<option value="pascal">Delphi/Pascal</option>',
				'<option value="scala">Scala</option>',
				'<option value="groovy">Groovy</option>',
				'<option value="lua">Lua</option>',
				'<option value="cpp">Google Go</option>',
				'<option value="as3">Flash/AS3/Flex</option>',
				'<option value="shell">Shell/批处理</option>',
				'<option value="">Other</option>',
				'</select>',
				'<label>&nbsp;&nbsp;<input type="checkbox" id="isPretty">使用prettify</label>',//1.加入自定义的选择按钮
				'</div>',
				'<textarea class="ke-textarea" style="width:408px;height:260px;"></textarea>',
				'</div>'].join(''),
			dialog = self.createDialog({
				name : name,
				width : 450,
				title : self.lang(name),
				body : html,
				yesBtn : {
					name : self.lang('yes'),
					click : function(e) {
						var type = K('.ke-code-type', dialog.div).val(),
							code = textarea.val(),
							
							//2.根据是否选择prettify生成相应的样式
				            cls = type === '' ? '' : (isPretty.checked ? (' lang-' + type) : type),
				            html = '<pre class="' + (isPretty.checked ? 'prettyprint':'brush:') + cls + '">\n' + K.escape(code) + '</pre>\n';

							
							//cls = type === '' ? '' :  ' lang-' + type,
							//html = '<pre class="prettyprint' + cls + '">\n' + K.escape(code) + '</pre> ';
						if (K.trim(code) === '') {
							alert(lang.pleaseInput);
							textarea[0].focus();
							return;
						}
						self.insertHtml(html).hideDialog().focus();
					}
				}
			}),
			textarea = K('textarea', dialog.div);
		textarea[0].focus();
	});
});
