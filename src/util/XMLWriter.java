package util;

import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLWriter {
	
	public static void write(String path, String ID, String tagName, String tagValue){

        try {
            // 1.得到DOM解析器的工厂实例
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // 2.从DOM工厂里获取DOM解析器
            DocumentBuilder db = dbf.newDocumentBuilder();
            // 3.解析XML文档，得到document，即DOM树
            Document doc = db.parse(path);
            NodeList list = doc.getElementsByTagName("ID");
            
            for(int i = 0; i < list.getLength(); i++){
            	Node node = list.item(i);
                if(node.getFirstChild().getNodeValue().equals(ID)){
                	Node parentNode = node.getParentNode();
					NodeList nChildList = parentNode.getChildNodes();
					for (int j = 0; j < nChildList.getLength(); j++) {
						if (Node.ELEMENT_NODE == nChildList.item(j).getNodeType()) {
							Node childNode = nChildList.item(j);
							if(childNode.getNodeName().equals(tagName)){
								if(!childNode.hasChildNodes()){
									childNode.appendChild(doc.createTextNode(tagValue));
								}else{
									childNode.getFirstChild().setNodeValue(tagValue);
								}
							}
						}
					}
					break;
                }
            }
            
            writeFile(doc, path);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
	
	public static void writeFile(Document doc, String path){
		try{
	        //保存xml文件
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        DOMSource domSource = new DOMSource(doc);
	        //设置编码类型
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        StreamResult result = new StreamResult(new FileOutputStream(path));
	        //把DOM树转换为xml文件
	        transformer.transform(domSource, result);
		} catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
	}

}
