<#macro default>
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org/">
    <head>
        <meta charset="UTF-8"/>
        <title>${title!"Encounter Generator"}</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
        <script src="https;/ajax.googleeapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    </head>

    <body class="container">

    <div>
    	<#if user??>
            <div>
                <h1">Hello <span>${user.name}</span></h1>
                <a href="/logout">Logout</a>
                <nav class="user-nav">
                    <a href="/">Home</a> |
                    <a href="/creature">Creatures</a> |
                    <a href="/encounters">Encounters</a> |
                    <a href="/interest">Interests</a> |
                    <a href="/treasure">Treasures</a> |
                    <a href="/login">Login</a>
                </nav>
            </div>
		<#else>
            <div>
                <nav>
                    <a href="/">Home</a> |
                    <a href="/encounters">Encounters</a> |
                    <a href="/login">Login</a>
                </nav>
            </div>
        </#if>    
    </div>
	<#nested>
    </body>
    </html>
</#macro>