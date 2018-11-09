/**
 * 
 */
package com.smart.util;

import org.hibernate.dialect.MySQL57InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StringType;

/**  
* @Description:
* @author raopanfeng 
* @date 2017年10月11日 下午4:04:55
*/
public class MySQLLocalDialect extends MySQL57InnoDBDialect {  
    
	public MySQLLocalDialect(){  
        super();  
        registerFunction("convert",new SQLFunctionTemplate(StringType.INSTANCE, "convert(?1 using ?2)") );  
    }  
}  