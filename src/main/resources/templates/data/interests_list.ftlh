<#import "../layout.ftlh" as layout>

<@layout.default>
<a href="/interest/add">Add a Point of Interest</a> |
<a href="/interest/remove">Remove a Point of Interest</a>

<table class="table">

    <tr>
        <th>Description</th>
        <th>UserID</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Shared</th>
    </tr>
<#list data as interest>
    <tr>
        <td>${interest.description}</td>
        <td>${interest.userid}</td>
        <td>${interest.createdate}</td>
        <td>${interest.updated}</td>
        <td>${interest.shared}</td>
        <td><a th:href="/interest/edit/${interest.id}">Edit</a></td>
    </tr>
</#list>

</table>
</@layout.default>