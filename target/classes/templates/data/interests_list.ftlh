<#import "../layout.ftlh" as layout>

<@layout.default>
<a href="/data/interests?add=true">Add a Point of Interest</a>

<table class="table">

    <tr>
        <th>Description</th>
        <th>UserID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Shared</th>
        <th/>
        <th/>
    </tr>
<#list data as interest>
    <tr>
        <td>${interest.description}</td>
        <td>${interest.userid}</td>
        <td>${interest.createdate}</td>
        <td>${interest.updated}</td>
        <td>${interest.shared}</td>
        <td><a href="/data/interests/${interest.id}">Edit</a></td>
        <td><a href="/data/interests/${interest.id}?action=delete">Delete</a></td>
    </tr>
</#list>

</table>
</@layout.default>