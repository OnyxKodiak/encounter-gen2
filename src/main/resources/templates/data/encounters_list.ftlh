<#import "../layout.ftlh" as layout>

<@layout.default>
<form method="post" style="max-width:600px;">
    <div class="form-group">
        <label for="partylevel">Party Level</label>
        <input type="number" class="form-control" name="partylevel" />
    </div>
    <div class="form-group">
        <label for="environment">Environment</label>
        <input class="form-control" name="environment" />
    </div>
    <div class="form-group">
        <label for="partysize">Party Size</label>
        <input  type="number" class="form-control" name="partysize" />
    </div>
    <div class="form-group">
        <label for="difficulty">Difficulty</label>
        <input class="form-control" name="difficulty" />
    </div>
    <div class="form-group">
        <label for="numencounters">Number of Encounters</label>
        <input  type="number" class="form-control" name="numencounters" />
    </div>
    <div class="form-group">
        <label for="frequency">Frequency</label>
        <input class="form-control" name="frequency" />
    </div>
    <div class="form-group">
        <label for="nummobs">Number of Creatures</label>
        <input  type="number" class="form-control" name="nummobs" />
    </div>
    <div class="form-group">
        <label for="userselect">User Content</label>
        <input type="checkbox" class="form-control" name="userselect" />
    </div>
    <div class="form-group">
        <label for="loot">Loot</label>
        <input type="checkbox" class="form-control" name="loot" />
    </div>


    <input type="submit" value="Roll the Dice!" />
</form>
</@layout.default>