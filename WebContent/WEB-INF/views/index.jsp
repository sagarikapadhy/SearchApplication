<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form method = "POST" modelAttribute="searchModel" action ="./search">
         <table>
            <tr>
               <td><form:label path = "keyword">Keyword</form:label></td>
               <td><form:input path = "keyword" /></td>
            </tr>
            <tr>
               <td><form:label path = "url">Url</form:label></td>
               <td><form:input path = "url"/></td>
            </tr>            
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Search"/>
               </td>
            </tr>
         </table>  
      </form:form>
</body>
</html>