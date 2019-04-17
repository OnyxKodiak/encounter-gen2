<#import "../layout.ftlh" as layout>

<@layout.default>
<a href="/data/treasure_create">Add Loot</a> |
<a href="/data/treasure_remove">Remove Loot</a>

<table class="table">

    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Value</th>
        <th>Description</th>
        <th>UserID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Shared</th>
    </tr>
<#list data as treasure>
    <tr>
        <td>${treasure.name}</td>
        <td>${treasure.type}</td>
        <td>${treasure.value}</td>
        <td>${treasure.description}</td>
        <td>${treasure.userid}</td>
        <td>${treasure.createdate}</td>
        <td>${treasure.updated}</td>
        <td>${treasure.shared}</td>
        <td><a href="/data/treasures/${treasure.id}">Edit</a></td>
    </tr>
</#list>

</table>
</@layout.default>