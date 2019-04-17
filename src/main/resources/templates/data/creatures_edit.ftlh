<#import "../layout.ftlh" as layout>

<@layout.default>
<form method="post" style="max-width:600px;" >
    <div class="form-group">
        <label for="name">Name</label>
        <input class="form-control" name="name" />
    </div>
    <div class="form-group">
        <label for="type">Type</label>
        <input class="form-control" name="type" />
    </div>
    <div class="form-group">
        <label for="cr">CR</label>
        <input class="form-control" name="cr" />
    </div>
    <div class="form-group">
        <label for="str">Str</label>
        <input class="form-control" name="str" />
    </div>
    <div class="form-group">
        <label for="dex">Dex</label>
        <input class="form-control" name="dex" />
    </div>
    <div class="form-group">
        <label for="con">Con</label>
        <input class="form-control" name="con" />
    </div>
    <div class="form-group">
        <label for="intl">Int</label>
        <input class="form-control" name="intl" />
    </div>
    <div class="form-group">
        <label for="wis">Wis</label>
        <input class="form-control" name="wis" />
    </div>
    <div class="form-group">
        <label for="cha">Cha</label>
        <input class="form-control" name="cha" />
    </div>
    <div class="form-group">
        <label for="ac">AC</label>
        <input class="form-control" name="ac" />
    </div>
    <div class="form-group">
        <label for="hp">HP</label>
        <input class="form-control" name="hp" />
    </div>
    <div class="form-group">
        <label for="size">Size</label>
        <input class="form-control" name="size" />
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <input class="form-control" name="description" />
    </div>
    <div class="form-group">
        <label for="shared">Shared</label>
        <input type="checkbox" class="form-control" name="shared" />
    </div>

    <input type="hidden" value="${creature.id}" name="id" />
    <input type="submit" value="Update Creature" />
</form>
</@layout.default>