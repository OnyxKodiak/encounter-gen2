<#import "layout.ftlh" as layout>

<@layout.default>
<form method="post" style="max-width:600px;">
    <div class="form-group">
        <label for="name">Name</label>
        <input class="form-control" name="name" />
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" name="password" />
    </div>
    <div class="form-group">
        <label for="verify">Verify Password</label>
        <input type="password" class="form-control" name="verify" />
    </div>

    <input type="submit" value="Register" />
</form>

</@layout.default>