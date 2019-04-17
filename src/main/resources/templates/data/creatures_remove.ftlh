<#import "../layout.ftlh" as layout>

<@layout.default>
<form method="post">

    <div th:each="creature : ${creature}" class="checkbox">
        <input type="checkbox" name="creatureIds" value="${creature.id}" id="${creature.id}"/>
        <label for="${creature.id}" name="${creature.name}"></label>
        <br />
    </div>

    <input type="submit" value="Remove Creature" />
</form>
</@layout.default>