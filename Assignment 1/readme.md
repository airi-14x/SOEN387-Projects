# war File

When deploying the application to Tomcat, a .war file is generated and can be found in the tomcat folder under webbapps/Assignment 1

# Running the HTTP server 

- Navigate to Assigmnment1/src/java/SoenServer.java
- Right click the SoenServer.java file
- Select "run"
- Open http://localhost:8009/ on any web browser 
- You can now navigate to the index pages index.html and index2.html

# Submitting the form

Once the form is submitted, the request in handled by the Http server. A context for the response is created and uses the class
RequestHandler. In this class, the handle() method verifies if the request is a POST or GET. It fetches the parameters from the query in case
of a get and displays them in a new page. For a post request, it sends the data to the server and displays the values on a redirected page. 


# CURL COMMAND 1 --- GET request to SoenServlet

curl -v get "http://localhost:8080/Assignment%201/SoenServlet?name=Jasmine+Latendresse&email=latendresse.jasmine%40gmail.com&formats=html"

# OUTPUT

* Rebuilt URL to: get/
* Could not resolve host: get
* Closing connection 0
curl: (6) Could not resolve host: get
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#1)
> GET /Assignment%201/SoenServlet?name=Jasmine+Latendresse&email=latendresse.jasmine%40gmail.com&formats=html HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: text/html;charset=UTF-8
< Content-Length: 874
< Date: Fri, 04 Oct 2019 23:42:27 GMT
< 
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head><title>Soen 387 Assignment 1</title></head>
<body bgcolor = "#f0f0f0">
<h1 align = "center">Soen 387 Assignment 1</h1>
<h3> Request Method: GET</h3>
<h3> Query String: name=Jasmine Latendresseemail=latendresse.jasmine@gmail.comformats=html</h3>
<table width = "100%" border = "1" align = "center">
<tr bgcolor = "#949494">
<th>Header Name</th><th>Header Value(s)</th>
</tr>

<tr><td>host</td>
<td> localhost:8080</td></tr>

<tr><td>user-agent</td>
<td> curl/7.54.0</td></tr>

<tr><td>accept</td>
<td> */*</td></tr>

<tr bgcolor = "#949494"><th>Query String</th><th bgcolor = "#949494">Parameter Values</th></tr>

<tr><td>name</td> 
<td>Jasmine Latendresse</td></tr>

<tr><td>email</td> 
<td>latendresse.jasmine@gmail.com</td></tr>

<tr><td>formats</td> 
<td>html</td></tr>

</table>
</body></html>
* Connection #1 to host localhost left intact

********************************************

# CURL COMMAND 2 --- POST request to SoenServlet

curl -v -XPOST "http://localhost:8080/Assignment%201/SoenServlet?name=Jasmine+Latendresse&email=latendresse.jasmine%40gmail.com&formats=xml"

# OUTPUT

*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /Assignment%201/SoenServlet?name=Jasmine+Latendresse&email=latendresse.jasmine%40gmail.com&formats=xml HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: text/html;charset=UTF-8
< Content-Length: 873
< Date: Sat, 05 Oct 2019 00:06:01 GMT
< 
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head><title>Soen 387 Assignment 1</title></head>
<body bgcolor = "#f0f0f0">
<h1 align = "center">Soen 387 Assignment 1</h1>
<h3> Request Method: POST</h3>
<h3> Query String: name=Jasmine Latendresseemail=latendresse.jasmine@gmail.comformats=xml</h3>
<table width = "100%" border = "1" align = "center">
<tr bgcolor = "#949494">
<th>Header Name</th><th>Header Value(s)</th>
</tr>

<tr><td>host</td>
<td> localhost:8080</td></tr>

<tr><td>user-agent</td>
<td> curl/7.54.0</td></tr>

<tr><td>accept</td>
<td> */*</td></tr>

<tr bgcolor = "#949494"><th>Query String</th><th bgcolor = "#949494">Parameter Values</th></tr>

<tr><td>name</td> 
<td>Jasmine Latendresse</td></tr>

<tr><td>email</td> 
<td>latendresse.jasmine@gmail.com</td></tr>

<tr><td>formats</td> 
<td>xml</td></tr>

</table>
</body></html>
* Connection #0 to host localhost left intact

*********************************************************

# CURL COMMAND 3 --- GET request to index.html on SoenServlet

curl -v get "http://localhost:8080/Assignment%201/index.html"

# OUTPUT

* Rebuilt URL to: get/
* Could not resolve host: get
* Closing connection 0
curl: (6) Could not resolve host: get
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#1)
> GET /Assignment%201/index.html HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 
< Accept-Ranges: bytes
< ETag: W/"1634-1570224394000"
< Last-Modified: Fri, 04 Oct 2019 21:26:34 GMT
< Content-Type: text/html
< Content-Length: 1634
< Date: Sat, 05 Oct 2019 00:08:00 GMT
< 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link rel="stylesheet" href="./css/css_file1.css" type="text/css" media="all"></link>
        <title>Index 1</title>
    </head>
    <body>
        <div class="container">
            <h2>Index 1</h2>
            <p>This is the index 1 page with the GET method</p>
            <hr></hr>
            <form name="get-form" method="get" action="/Assignment 1/SoenServlet">
                <label for="name"><b>Name</b></label>
                <input type="text" placeholder="Enter Name" name="name" required></input>
                
                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required></input>
                <p><b>Format</b>
                    <select name = "formats">
                        <option value="not specified"></option>
                        <option value="html">html</option>
                        <option value="text">plain text</option>
                        <option value="xml">xml</option>
                    </select> 
                </p>
                <hr></hr>
                <button type="submit" class="btn">Send Data</button>
            </form>
            <p>Go to <a href="./index2.html">Index 2</a> page</p>
        </div>
    </body>
</html>
* Connection #1 to host localhost left intact

*********************************************

# CURL COMMAND 4 ---  POST request on HTTP server

curl -v -XPOST "http://localhost:8080/Assignment%201/SoenServlet?name=Jasmine+Latendresse&email=latendresse.jasmine%40gmail.com&formats=text"

# OUTPUT

*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> POST /Assignment%201/SoenServlet?name=Jasmine+Latendresse&email=latendresse.jasmine%40gmail.com&formats=text HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
> 
< HTTP/1.1 200 
< Content-Type: text/html;charset=UTF-8
< Content-Length: 875
< Date: Sat, 05 Oct 2019 00:24:32 GMT
< 
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<html>
<head><title>Soen 387 Assignment 1</title></head>
<body bgcolor = "#f0f0f0">
<h1 align = "center">Soen 387 Assignment 1</h1>
<h3> Request Method: POST</h3>
<h3> Query String: name=Jasmine Latendresseemail=latendresse.jasmine@gmail.comformats=text</h3>
<table width = "100%" border = "1" align = "center">
<tr bgcolor = "#949494">
<th>Header Name</th><th>Header Value(s)</th>
</tr>

<tr><td>host</td>
<td> localhost:8080</td></tr>

<tr><td>user-agent</td>
<td> curl/7.54.0</td></tr>

<tr><td>accept</td>
<td> */*</td></tr>

<tr bgcolor = "#949494"><th>Query String</th><th bgcolor = "#949494">Parameter Values</th></tr>

<tr><td>name</td> 
<td>Jasmine Latendresse</td></tr>

<tr><td>email</td> 
<td>latendresse.jasmine@gmail.com</td></tr>

<tr><td>formats</td> 
<td>text</td></tr>

</table>
</body></html>
* Connection #0 to host localhost left intact



