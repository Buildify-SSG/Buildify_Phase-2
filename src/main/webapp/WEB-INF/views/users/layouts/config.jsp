<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
<meta name="author" content="AdminKit">
<meta name="keywords" content="adminkit, bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

<link rel="preconnect" href="https://fonts.gstatic.com">
<link rel="shortcut icon" href="<c:url value='/static/img/icons/icon-48x48.png' />" />

<link rel="canonical" href="https://demo-basic.adminkit.io/pages-blank.html" />
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

<title>Buildify</title>
<script>
    window.addEventListener("DOMContentLoaded", function() {
        if (localStorage.getItem("darkMode") === "enabled") {
            document.documentElement.classList.add("dark-mode");
        }
    });
</script>

<link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
<link href="<c:url value='/static/css/custom.css' />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&display=swap" rel="stylesheet">