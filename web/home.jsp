<%-- 
    Document   : home
    Created on : Mar 6, 2022, 6:37:05 PM
    Author     : BK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="Bk" />
        <title>Simple Sidebar - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/styles.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
        <script src="https://unpkg.com/infinite-scroll@4/dist/infinite-scroll.pkgd.js"></script>
        <script>
            function submitForm()
            {
                document.getElementById("frmSearch").submit();
            }
        </script
    </head>
    <body>
        <div class="d-flex" id="wrapper">
            <!-- Sidebar-->
            <div class="border-end bg-white" id="sidebar-wrapper">
                <nav>
                                <c:if test="${empty account}">
                    <div class="sidebar-heading border-bottom bg-light">Please Login</div>
                                </c:if>
                                <c:if test="${not empty account}">
                                    <div class="sidebar-heading border-bottom bg-light">Hello ${sessionScope.account.username}</div>
                                </c:if>
                                    <div class="list-group list-group-flush">
           <a class="list-group-item list-group-item-action list-group-item-light p-3" href="?rel=1&opage=${requestScope.ocurrentPage}&cpage=${requestScope.ccurrentPage}&ppage=${requestScope.pcurrentPage}&apage=${requestScope.acurrentPage}" data-rel="1">Dashboard</a>
                        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="?rel=2&opage=${requestScope.ocurrentPage}&cpage=${requestScope.ccurrentPage}&ppage=${requestScope.pcurrentPage}&apage=${requestScope.acurrentPage}" data-rel="2">Company</a>
                        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="?rel=3&opage=${requestScope.ocurrentPage}&cpage=${requestScope.ccurrentPage}&ppage=${requestScope.pcurrentPage}&apage=${requestScope.acurrentPage}" data-rel="3">Product</a>
                        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="?rel=4&opage=${requestScope.ocurrentPage}&cpage=${requestScope.ccurrentPage}&ppage=${requestScope.pcurrentPage}&apage=${requestScope.acurrentPage}" data-rel="4">Account</a>
                        </ul>
                    </div>
                </nav>
            </div>
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <!-- Top navigation-->
                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <div class="container-fluid">
                        <button class="btn btn-primary" id="sidebarToggle">&#9776;</button>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav ms-auto mt-2 mt-lg-0">
                                 <c:if test="${empty account}">
                                <li class="nav-item"><a class="nav-link" href="../login?method=login">Login</a></li>
                                </c:if>
                                     <c:if test="${not empty account}">
                                         <li class="nav-item"><a class="nav-link" href="../login?method=logout">Logout</a></li>
                                         </c:if>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- Page content-->
                <div class="container-fluid">
                    <section>
                        <article>
                            <div class="row">
                                <div class="col-sm-9">
                                    <h2>Manage <b>Invoice</b></h2>
                                </div>
                                <div class="col-sm-3">
                                <c:if test="${requestScope.insert gt 0}">
                                    <a href="insert" class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Order</span></a>
                                    </c:if>
                                </div>
                            </div>
                            <form action="search" method="GET" id="frmSearch">
            Search By Invoice: 
            <select name="ivd" onchange="submitForm();">
                <option value="0">----SELECT A INVOICE----</option>
                <c:forEach items="${requestScope.invoice}" var="v">
                <option
                    ${(v.invoice_id==requestScope.ivd)?"selected=\"selected\"":""}
                    value="${v.invoice_id}">${v.invoice_id}</option>
                </c:forEach>
            </select>
        </form>
                            <h1 class="mt-4">Invoice Table</h1>
                            <c:if test="${requestScope.order.size() gt 0}">
                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <td scope="col">Invoice ID</td>
                                            <td scope="col">Company ID</td>
                                            <td scope="col">Product ID</td>
                                            <td scope="col">Amount</td>
                                            <td scope="col">Cost</td>
                                            <td scope="col">Import Date</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.order}" var="o">
                                            <tr>
                                                <td>${o.invoice_id}</td>
                                                <td>${o.c.companyid}</td>
                                                <td>${o.p.productid}</td>
                                                <td>${o.amount}</td>
                                                <td>${o.cost}</td>
                                                <td><fmt:formatDate pattern = "yyyy/MMM/dd" 
                                                                value = "${o.importDate}" /></td>
                                                        <c:if test="${requestScope.update gt 0}">
                                                        <td><a href="update?ivd=${o.invoice_id}&cid=${o.c.companyid}&pid=${o.p.productid}&a=${o.amount}&c=${o.cost}&idate=${o.importDate}" class="btn btn-primary" data-toggle="modal"> Update</td>
                                                    </c:if>
                                        <c:if test="${requestScope.delete gt 0}">
                                        <td><button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#mymodal${o.invoice_id}${o.p.productid}${o.importDate}">Delete</button>
                                        <div class="modal fade" id="mymodal${o.invoice_id}${o.p.productid}${o.importDate}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog">
                                                                           <div class="modal-content">
                                                                           <div class="modal-header">
                                                                           <h5 class="modal-title" id="exampleModalLabel">Deleting Entry</h5>
                                                                           <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                          </div>                                                
                                                      <div class="modal-body">
                                                        Are you sure you want to delete this entry?
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Return</button>
                                                        <a href="delete?ivd=${o.invoice_id}&cid=${o.c.companyid}&pid=${o.p.productid}&a=${o.amount}&c=${o.cost}&idate=${o.importDate}" class="btn btn-primary" role="button">Delete</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        </td>
                                    </c:if>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                
                            </c:if>
                                <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                   </li>
                          <c:forEach begin="1" end="${requestScope.onoOfPages}" varStatus="loop">
                                   <li class="page-item ${(loop.index==requestScope.ocurrentPage)?"active":""}" ${(loop.index==requestScope.ocurrentPage)?"":""}><a class="page-link" href="search?rel=${sessionScope.rel}&opage=${loop.index}&cpage=$${requestScope.ccurrentPage}&ppage=${requestScope.pcurrentPage}&apage=${requestScope.acurrentPage}">${loop.index}</a></li>
                                   </c:forEach>
                                   <li class="page-item">
                                   </li>
                                 </ul>
                               </nav>
                            <c:if test="${requestScope.order.size() eq 0}">
                                No record to display. <br/>
                            </c:if>
                            <div id="ocontainerbot" class="pagger"></div>
                        </article>
                    </section>

                    <section>
                        <article>
                        <div class="row">
                                
                            </div>
                            <div class="row">
                                <div class="col-sm-9">
                                    <h2>Manage <b>Company</b></h2>
                                </div>
                                <div class="col-sm-3">
                                <c:if test="${requestScope.insert gt 0}">
                                   <a href="company/insert" class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Company</span></a>
                                    </c:if>
                                </div>
                            </div>
                            <h1 class="mt-4">Company Table</h1>
                            <c:if test="${requestScope.coms.size() gt 0}">
                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <td scope="col">Company Name</td>
                                            <td scope="col">Company ID</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <div class="container">
                                        <c:forEach items="${requestScope.coms}" var="c">
                                            <article class="port">
                                            <tr>
                                                <td>${c.company}</td>
                                                <td>${c.companyid}</td>
                                            </tr>
                                            </article>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${requestScope.coms.size() eq 0}">
                                No record to display. <br/>
                            </c:if>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                   </li>
                          <c:forEach begin="1" end="${requestScope.cnoOfPages}" varStatus="loop">
                                      <li class="page-item ${(loop.index==requestScope.ccurrentPage)?"active":""}" ${(loop.index==requestScope.ccurrentPage)?"":""}><a class="page-link" href="search?rel=${sessionScope.rel}&opage=${requestScope.ocurrentPage}&cpage=${loop.index}&ppage=${requestScope.pcurrentPage}&apage=${requestScope.acurrentPage}">${loop.index}</a></li>
                                   </c:forEach>
                                   <li class="page-item">
                                   </li>
                                 </ul>
                               </nav>
                        </article>
                    </section>
                    
                    
                    
                    <section>
                        <article>
                        <div class="row">
                                
                            </div>
                            <div class="row">
                                <div class="col-sm-9">
                                    <h2>Manage <b>Product</b></h2>
                                </div>
                                <div class="col-sm-3">
                                <c:if test="${requestScope.insert gt 0}">
                                    <a href="product/insert" class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                                    </c:if>
                                </div>
                            </div>
                            <h1 class="mt-4">Product Table</h1>
                            <c:if test="${requestScope.prods.size() gt 0}">
                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <td scope="col">Product Name</td>
                                            <td scope="col">Product ID</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.prods}" var="p">
                                            <tr>
                                                <td>${p.productname}</td>
                                                <td>${p.productid}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                                <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                   </li>
                          <c:forEach begin="1" end="${requestScope.pnoOfPages}" varStatus="loop">
                                   <li class="page-item ${(loop.index==requestScope.pcurrentPage)?"active":""}" ${(loop.index==requestScope.pcurrentPage)?"":""}><a class="page-link" href="search?rel=${sessionScope.rel}&opage=${requestScope.ocurrentPage}&cpage=${requestScope.ccurrentPage}&ppage=${loop.index}&apage=${requestScope.acurrentPage}">${loop.index}</a></li>
                                   </c:forEach>
                                   <li class="page-item">
                                   </li>
                                 </ul>
                               </nav>
                            <c:if test="${requestScope.coms.size() eq 0}">
                                No record to display. <br/>
                            </c:if>
                            
                        </article>
                    </section>
                    
                    
                    <section>
                        <article>
                        <div class="row">
                                
                            </div>
                            <div class="row">
                                <div class="col-sm-9">
                                    <h2>Manage <b>Account</b></h2>
                                </div>
                                <div class="col-sm-3">
                                <c:if test="${requestScope.insert gt 0}">
                                    <a href="product/insert" class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>
                                    </c:if>
                                </div>
                            </div>
                            <h1 class="mt-4">Account Table</h1>
                            <c:if test="${requestScope.accs.size() gt 0}">
                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <td scope="col">Account Name</td>
                                            <td scope="col">Permission</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.accs}" var="a">
                                            <tr>
                                                <td>${a.username}</td>
                                                <td>${a.permission}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                                <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-center">
                                    <li class="page-item disabled">
                                   </li>
                          <c:forEach begin="1" end="${requestScope.anoOfPages}" varStatus="loop">
                                      <li class="page-item ${(loop.index==requestScope.acurrentPage)?"active":""}" ${(loop.index==requestScope.acurrentPage)?"":""}><a class="page-link" href="search?rel=${sessionScope.rel}&opage=${requestScope.ocurrentPage}&cpage=${requestScope.ccurrentPage}&ppage=${requestScope.pcurrentPage}&apage=${loop.index}">${loop.index}</a></li>
                                   </c:forEach>
                                   <li class="page-item">
                                   </li>
                                 </ul>
                               </nav>
                            <c:if test="${requestScope.accs.size() eq 0}">
                                No record to display. <br/>
                            </c:if>
                            
                        </article>
                    </section>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="../js/scripts.js"></script>
        <script src="js/scripts.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script>    

                    var a = ${sessionScope.rel}
                   $(document).ready(function()
                    {
                $('section:nth-of-type(1)').stop().fadeOut(0, 'linear');
                $('section:nth-of-type(' + a + ')').stop().fadeIn(400, 'linear'); //show initial friends list

            });
        </script>
    </body>
</html>

