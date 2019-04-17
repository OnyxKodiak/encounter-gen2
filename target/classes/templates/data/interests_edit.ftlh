<#import "../layout.ftlh" as layout>

<@layout.default>
<form method="post" style="max-width:600px;" >
    <div class="form-group">
        <label for="name">Name</label>
        <input class="form-control" name="name" />
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <input class="form-control" name="description" />
    </div>
    <div class="form-group">
        <label for="shared">Shared</label>
        <input type="checkbox" class="form-control" name="shared" />
    </div>

    <input type="hidden" value="${interest.id}" name="id" />
    <input type="submit" value="Update Point of Interest" />
</form>
</@layout.default>