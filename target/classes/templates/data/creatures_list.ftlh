<#import "../layout.ftlh" as layout>

<@layout.default>
<a href="/data/creatures?add=true">Add a Creature</a>

<table class="table">

    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>CR</th>
        <th>Str</th>
        <th>Dex</th>
        <th>Con</th>
        <th>Int</th>
        <th>Wis</th>
        <th>Cha</th>
        <th>AC</th>
        <th>HP</th>
        <th>Size</th>
        <th>Description</th>
        <th>UserID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Shared</th>
        <th/>
        <th/>
    </tr>
<#list data as creature>
    <tr>
        <td>${creature.name}</td>
        <td>${creature.type}</td>
        <td>${creature.cr}</td>
        <td>${creature.str}</td>
        <td>${creature.dex}</td>
        <td>${creature.con}</td>
        <td>${creature.intl}</td>
        <td>${creature.wis}</td>
        <td>${creature.cha}</td>
        <td>${creature.ac}</td>
        <td>${creature.hp}</td>
        <td>${creature.size}</td>
        <td>${creature.description}</td>
        <td>${creature.userid}</td>
        <td>${creature.createdate}</td>
        <td>${creature.updated}</td>
        <td>${creature.shared}</td>
        <td><a href="/data/creatures/${creature.id}">Edit</a></td>
        <td><a href="/data/creatures/${creature.id}?action=delete">Delete</a></td>
    </tr>
</#list>

</table>

</@layout.default>