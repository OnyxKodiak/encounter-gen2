<#import "../layout.ftlh" as layout>

<@layout.default>
<form method="post">

    <div th:each="treasure : ${treasure}" class="checkbox">
        <input type="checkbox" name="treasureIds" value="${treasure.id}" id="${treasure.id}"/>
        <label for="${treasure.id}" name="${treasure.name}"></label>
        <br />
    </div>

    <input type="submit" value="Remove Creature" />
</form>
</@layout.default>