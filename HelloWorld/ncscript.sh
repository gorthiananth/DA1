echo "
table="$1"
FDW=1
start="$2"
end="$3"
column="$4"
service="$5"
objtype=cpcode
object="$6"
groupby="$7"
orderby="${10}" "${11}"
limit="$8"
END" | nc "$9" 8457