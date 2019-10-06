## A1 - Questions

```Airi Chow (#40003396)
 SOEN 387 - Fall 2019
 October 5th 2019
```

*****
#### Task 3 Questions
- Examine if a war file is generated for your project. How was it generated?
Where is the location of the generated war file?
> War file is created when the web application is compiled successfully. It is located in the dist folder.

- How do you run the HttpServer class in Task 3? What are the deployment
requirements (i.e. in case the application is run on a different machine)?
> Right-Click and select "Run File". Open a web browser and type: localhost:8500
> On another machine, it should still run on localhost:8500 unless that port is in use. If that is the case,
  InetSocketAddress(8500) should be changed to another 4-digit port number.

- What happens when you submit the html forms in Task 3?
> The form is submitted to HelloWorldServlet to be processed. The output will vary based on the request method,
  the parameters submitted and the format chosen.

*****
#### Task 4 Curl Outputs

- Provide the four curl outputs in Task 4.

1. `curl get -v -H "format:html" http://localhost:8080/A1-Airi/HelloWorldServlet`

```
* Rebuilt URL to: get/
* Could not resolve host: get
* Closing connection 0
curl: (6) Could not resolve host: get
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#1)
> GET /A1-Airi/HelloWorldServlet HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> format:html
>
< HTTP/1.1 200
< Content-Type: text/html;charset=UTF-8
< Content-Length: 708
< Date: Sun, 06 Oct 2019 00:20:26 GMT
<
<!DOCTYPE html>
<html>
<head>
<title>Servlet HelloWorldServlet</title>
<style>
table { border-collapse: collapse; }
th, td { border: 1px solid black; }
</style>
</head>
<body>
<h1>Servlet HelloWorldServlet at /A1-Airi</h1>
<table>
<tr>
<th>&nbspRequest Method:&nbsp&nbsp
</th>
<td>&nbspGET
&nbsp</td>
</tr>
</table>
&nbsp
<table>
<tr>
<th>&nbspRequest Headers:&nbsp</th>
</tr>
<tr>
<td>&nbspHost</td>
<td>&nbsplocalhost:8080&nbsp</td>
</tr>
</tr>
<tr>
<td>&nbspConnection</td>
<td>&nbspnull&nbsp</td>
</tr>
</tr>
<tr>
<td>&nbspUser-Agent</td>
<td>&nbspcurl/7.43.0&nbsp</td>
</tr>
</tr>
<tr>
<td>&nbspAccept</td>
<td>&nbsp*/*&nbsp</td>
</tr>
</table>
&nbsp
<table>
<tr>
<th>&nbspQuery String:&nbsp</th>
</tr>
* Connection #1 to host localhost left intact
```

2. `curl -XPOST -v -H "format:xml" http://localhost:8080/A1-Airi/HelloWorldServlet`

```
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> POST /A1-Airi/HelloWorldServlet HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
> format:xml
>
< HTTP/1.1 200
< Content-Type: text/html;charset=UTF-8
< Content-Length: 709
< Date: Sun, 06 Oct 2019 00:26:41 GMT
<
<!DOCTYPE html>
<html>
<head>
<title>Servlet HelloWorldServlet</title>
<style>
table { border-collapse: collapse; }
th, td { border: 1px solid black; }
</style>
</head>
<body>
<h1>Servlet HelloWorldServlet at /A1-Airi</h1>
<table>
<tr>
<th>&nbspRequest Method:&nbsp&nbsp
</th>
<td>&nbspPOST
&nbsp</td>
</tr>
</table>
&nbsp
<table>
<tr>
<th>&nbspRequest Headers:&nbsp</th>
</tr>
<tr>
<td>&nbspHost</td>
<td>&nbsplocalhost:8080&nbsp</td>
</tr>
</tr>
<tr>
<td>&nbspConnection</td>
<td>&nbspnull&nbsp</td>
</tr>
</tr>
<tr>
<td>&nbspUser-Agent</td>
<td>&nbspcurl/7.43.0&nbsp</td>
</tr>
</tr>
<tr>
<td>&nbspAccept</td>
<td>&nbsp*/*&nbsp</td>
</tr>
</table>
&nbsp
<table>
<tr>
<th>&nbspQuery String:&nbsp</th>
</tr>
* Connection #0 to host localhost left intact
```

3. `curl get -v http://localhost:8080/A1-Airi/index.html`

```
* Rebuilt URL to: get/
* Could not resolve host: get
* Closing connection 0
curl: (6) Could not resolve host: get
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#1)
> GET /A1-Airi/index.html HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.43.0
> Accept: */*
>
< HTTP/1.1 200
< Accept-Ranges: bytes
< ETag: W/"1351-1570320237000"
< Last-Modified: Sun, 06 Oct 2019 00:03:57 GMT
< Content-Type: text/html
< Content-Length: 1351
< Date: Sun, 06 Oct 2019 00:29:56 GMT
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
        <title>Index - GET method</title>
        <link rel = "stylesheet" type = "text/css" href="css/css-file1.css"></link>
    </head>
    <body>
        <h2>Index - GET method</h2>
        <form name = "GETForm" method = "get" action="HelloWorldServlet">
            <p>Name:&emsp;<input type = "text" name = "name"></input></p>
            <p>Email:&emsp;<input type = "text" name = "email"></input></p>
            <p>Format:&emsp;
                <select name = "format">
                    <option value = "not specified"></option>
                    <option value = "html">html</option>
                    <option value = "text">plain text</option>
                    <option value = "xml">xml</option>
                </select>
            </p>
            <p><button type="submit" class="btn">Submit</button></p>
        </form>
        <p>Go to <a href="index2.html">POST's</a> page.</p>
        <p>Go to <a href="HomePage.html">Servelet's</a> Home page.</p>
    </body>
</html>
* Connection #1 to host localhost left intact
```

4. `curl -XPOST -v -H "format:text" http://localhost:8500`

```
* Rebuilt URL to: http://localhost:8500/
*   Trying ::1...
* Connected to localhost (::1) port 8500 (#0)
> POST / HTTP/1.1
> Host: localhost:8500
> User-Agent: curl/7.43.0
> Accept: */*
> format:text
>
< HTTP/1.1 200 OK
< Date: Sun, 06 Oct 2019 00:35:38 GMT
< Content-type: text/html
< Content-length: 1320
<
* Connection #0 to host localhost left intact
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><!--To change this license header, choose License Headers in Project Properties.To change this template file, choose Tools | Templatesand open the template in the editor.--><html xmlns="http://www.w3.org/1999/xhtml">    <head>        <title>Index - GET method</title>        <link rel = "stylesheet" type = "text/css" href="css/css-file1.css"></link>    </head>    <body>        <h2>Index - GET method</h2>        <form name = "GETForm" method = "get" action="HelloWorldServlet">            <p>Name:&emsp;<input type = "text" name = "name"></input></p>            <p>Email:&emsp;<input type = "text" name = "email"></input></p>            <p>Format:&emsp;                <select name = "format">                    <option value = "not specified"></option>                    <option value = "html">html</option>                    <option value = "text">plain text</option>                    <option value = "xml">xml</option>                </select>             </p>            <p><button type="submit" class="btn">Submit</button></p>        </form>        <p>Go to <a href="index2.html">POST's</a> page.</p>        <p>Go to <a href="HomePage.html">Servelet's</a> Home page.</p>    </body></html>
```
