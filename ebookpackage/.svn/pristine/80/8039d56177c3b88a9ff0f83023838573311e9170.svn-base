package com.whty.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class FileUtil {

	private static Logger logger=Logger.getLogger(FileUtil.class);

	//磁盘绝对路径
	public static String ABSOLUTE_PATH;
	

	/**
	 * @category 创建目录
	 * @param path
	 * @return
	 */
	public static boolean checkMkdirs(String path){
		File folder = new File(path);
		if(!folder .exists()  && !folder .isDirectory()){
			folder.mkdirs();
			return true;
		}
		else
			return false;
		
	}
	/**
	 *@category 2.获得指定目录下的所有文件
	 * @param filepath
	 */
	public static List<Map<String,String>> initFileList(String filepath){
		 List<Map<String,String>> fileList =null;
		try{
			fileList = Order(readfile(filepath));
		}catch(IOException ex){
			fileList= null;
		}
		return fileList;
	}
	
	 /**
     * @category 读取某个文件夹下的所有文件
     */
    private static List<Map<String,String>> readfile(String filepath) throws FileNotFoundException, IOException {
    	List<Map<String,String>> it = new ArrayList<Map<String,String>>();    
    	try {
            		Map<String,String> map=null;
                    File file = new File(filepath);
                    if (!file.isDirectory()) { //如果是单个文件
                    	map = new HashMap<String,String>();
                    	map.put("name", file.getName());
                    	map.put("path", file.getPath());
                    	map.put("absolutepath", file.getAbsolutePath());
                         it.add(map);

                    } else if (file.isDirectory()) {//如果是文件夹目录
                            String[] filelist = file.list();
                            for (int i = 0; i < filelist.length; i++) {
                                    File readfile = new File(filepath + File.separator + filelist[i]);
                                    if (!readfile.isDirectory()) {
                                            map = new HashMap<String,String>();
                                        	map.put("name", readfile.getName());
                                        	map.put("path", readfile.getPath());
                                        	map.put("absolutepath", readfile.getAbsolutePath());
                                             it.add(map);

                                    } else if (readfile.isDirectory()) {
                                            readfile(filepath + File.separator + filelist[i]);
                                    }
                            }

                    }

            } catch (FileNotFoundException e) {
            	logger.error("readfile()   Exception:" + e.getMessage());
            }
            return it;
    }
    
    private static List<Map<String,String>> Order(List<Map<String,String>> it){

		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(int k=0;k<it.size();k++){
			
			for(int i=0;i<it.size();i++){
				Map<String,String> m = (Map<String,String>)it.get(i);
				String name = m.get("name");
				//name=name.substring(0, name.lastIndexOf("."));
				String indexStr = name.substring(name.lastIndexOf("_")+1,name.length());
				int ind = Integer.parseInt(indexStr);
				if(k+1==ind)
					list.add(m);
			}
		
		}
		return list;
	}
    /**
     * @category 删除文件夹已经下面的文件
     * @param file
     */
    public static boolean deleteFile(File file){ 
    	boolean chk = false;
    	try{
    	  
    	   if(file.exists()){ 
    	    if(file.isFile()){ 
    	    	chk= file.delete(); 
    	    }else if(file.isDirectory()){ 
    	     File files[] = file.listFiles(); 
    	     for(int i=0;i<files.length;i++){ 
    	      deleteFile(files[i]); 
    	     } 
    	    } 
    	    chk=file.delete(); 
    	   
    	   }
    	}catch(Exception ex){
    		ex.printStackTrace();
    		chk = false;
    	}
    	   
    	   return chk;
    	} 
    
    /**
     * 获取文件大小
     */
    public static long getFileSize(String inputPath){
    	File f= new File(inputPath);  
        if (f.exists() && f.isFile()){   
            return f.length();
        }else{  
            return 0; 
        }  
    }
    /**
	 * 读取文本文件内容，以行的形式读取
	 * 
	 * @param String
	 *            filePathAndName 带有完整绝对路径的文件名
	 * @return String 返回文本文件的内容
	 */
	 public static StringBuffer readFileContent(String filePathAndName,String encoding)
	   throws IOException {
	  return readFileContent(filePathAndName,encoding,null,1024);
	 }
	 /**
		 * 读取文本文件内容，以行的形式读取
		 * 
		 * @param String
		 *            filePathAndName 带有完整绝对路径的文件名
		 * @param String
		 *            encoding 文本文件打开的编码方式 例如 GBK,UTF-8
		 * @param String
		 *            sep 分隔符 例如：#，默认为\n;
		 * @param int
		 *            bufLen 设置缓冲区大小            
		 * @return String 返回文本文件的内容
		 */
		 private static StringBuffer readFileContent(String filePathAndName, String encoding, String sep,int bufLen )
		   throws IOException {
			 StringBuffer str = new StringBuffer("");
		  if (filePathAndName == null || filePathAndName.equals("")) {
		   return str;
		  }
		  if(sep==null||sep.equals(""))
		  {
		   sep="\n";
		  }
		  if(!new File(filePathAndName).exists())
		  {
		   return str;
		  }
		  
		  FileInputStream fs = null;
		  InputStreamReader isr = null;
		  BufferedReader br = null;
		  try {
		   fs = new FileInputStream(filePathAndName);
		   if (encoding == null||encoding.trim().equals("")) {
		    isr = new InputStreamReader(fs);
		   } else {
		    isr = new InputStreamReader(fs, encoding.trim());
		   }
		   br = new BufferedReader(isr,bufLen);
		   String data = "";
		   while ((data = br.readLine()) != null) {
		    str.append(data).append(sep);
		   // str.append("#");
		   }
		  } catch (IOException e) {
		   throw e;
		  } finally {
		   try
		   {
		   if (br != null)
		    br.close();
		   if (isr != null)
		    isr.close();
		   if (fs != null)
		    fs.close();
		   }catch(IOException e)
		   {
		    throw e;
		   }
		  }

		  return str;
		 }
    
    /** 
     * 写文件 
     *  
     * @param s 
     */  
	public static long writeFile(String HTML,String fileName) {  
        FileOutputStream fos = null;  
        BufferedWriter bw = null;  
        long filesize =0;
        try {  
            File file = new File(fileName);  
            fos = new FileOutputStream(file);  
            bw = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));  
            bw.write(HTML);  
            filesize = HTML.getBytes().length;
        } catch (FileNotFoundException fnfe) {  
        	filesize=0;
            fnfe.printStackTrace();  
        } catch (IOException ioe) {  
        	filesize=0;
            ioe.printStackTrace();  
        } finally {  
            try {  
                if (bw != null)  
                    bw.close();  
                if (fos != null)  
                    fos.close();  
            } catch (IOException ie) {  
            }  
        }  
        return filesize;
    }  
}
