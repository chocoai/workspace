package com.whty.page.dialect;
import java.util.ArrayList;
import java.util.List;
public class SQLServerDialect extends Dialect {
    private List<String> lOrders = null;
    private boolean isDistinct =false;
    private int offset = 1;
    private int limit= 10;
    private String sql = "";
    private String partSQL = "";

    public SQLServerDialect(String sql,int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
        this.sql = sql.toLowerCase().replaceAll("order\\s{2,}by","order by");
        isDistinct = this.sql.toLowerCase().startsWith("select distinct");
        if (isDistinct){
            this.partSQL = this.sql.substring(15);
        }else{
            this.partSQL = this.sql.substring(6);
        }
    }

    /**
     * 将SQL语句变成一条语句，并且每个单词的间隔都是1个空格
     *
     * @param sql SQL语句
     * @return 如果sql是NULL返回空，否则返回转化后的SQL
     */
    private  String getLineSql(String sql) {
        return sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ");
    }

    private String getOrderByPart() {
        this.sql = this.sql.toLowerCase();
        int orderByIndex = this.sql.indexOf("order by");
        if (orderByIndex != -1) {
            // if we find a new "order by" then we need to ignore
            // the previous one since it was probably used for a subquery
            return this.sql.substring(orderByIndex+8);
        } else {
            return "";
        }
    }
    public String getLimitString(){
        if(!getOrderByPart().equals("")) {
            getOrderFields(getOrderByPart());
            return this.getSQLById();
        }
        else{
            return this.getSQLByTempTable(this.sql,offset,limit);
        }
    }

    private String getSQLByTempTable(String querySqlString,int offset,int limit){
//        OrderParser order=new OrderParser(this.aOrderBy[0]);
//        String sTempTableName="#temp_table_"+((int)(655536*Math.random()));
//        String sql="";
//        String sNewFieldList=order.addOrderField(this.aFields[0]);
//        this.aFields[0]=sNewFieldList;
//        String sSubTable=querySqlString.toLowerCase();
//        String sTempOrder=order.getOrder(true, false);
//        boolean bDistinct=sSubTable.substring(6).trim().toLowerCase().startsWith("distinct");
//        sSubTable = sSubTable.substring(bDistinct?(sSubTable.indexOf("distinct")+8):6).trim();
//
//        sSubTable="select "+(bDistinct?"distinct":"")+" top 100 percent "+sSubTable;
//
//        sql="set nocount on\n";
//        sql+="select temp_tbl.*,IDENTITY(int,1,1) as temp_ident_field into "+sTempTableName+" \nFrom (\n"+sSubTable+") as temp_tbl \n";
//        if(sTempOrder.length()>0){
//            sql+=" order by "+sTempOrder+" \n";
//        }
//        sql+="select * from "+sTempTableName+" where temp_ident_field between "+((this.nCurrentPage-1)*this.nPageSize+1)+" and "+(this.nCurrentPage*this.nPageSize)+"\n";
//        sql+="drop table "+sTempTableName+"\n";
//        sql+="set nocount off\n";
        return "";
    }

    private String getSQLById(){
        String isql="select "+(isDistinct?"distinct":"")+" top "+this.limit + partSQL +" ";
        isql="select top "+this.offset+" * from (\n"+isql+"\n) order_tbl1 \norder by "+getOrderString("order_tbl1")+" ";
        isql="select * from (\n"+isql+"\n) order_tbl2 \norder by "+getOrderString("order_tbl2");
//        return isql;
        System.out.println(isql);
        return isql;
    }

    public void getOrderFields(String sOrderBy){
        if(lOrders==null) {
            lOrders = new ArrayList<String>();
        }
        //System.out.println(sOrderBy);
        String[] aOrder = sOrderBy.split(",");
        for (int i = 0; i < aOrder.length; i++) {
            lOrders.add(aOrder[i].replaceAll("^[\\s{1,}]", "").replaceAll("^\\w+\\w*?\\.{1}", ""));
        }
        //System.out.println(lOrders.toString());
    }
    public String getOrderString(String sTableName){
        StringBuffer stringBuffer = new StringBuffer();
        if (lOrders == null) {
            return "";
        }else {
            for (int i = 0; i < lOrders.size(); i++) {
                if (i!=0)stringBuffer.append(",");
                stringBuffer.append(sTableName + "." + lOrders.get(i).toString().trim());
            }
        }
        return stringBuffer.toString();
    }

}
